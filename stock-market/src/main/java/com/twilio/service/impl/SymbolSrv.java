package com.twilio.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.twilio.model.Symbol;
import com.twilio.repo.ISymbolRepo;
import com.twilio.service.ISymbolSrv;

@Service
public class SymbolSrv implements ISymbolSrv {

	@Value("${twilio.alpha.url}")
	private String alphaUrl;

	@Value("${twilio.alpha.key}")
	private String alphaKey;

	@Value("${twilio.alpha.lastRefreshed}")
	private String alphaLastRefreshed;

	@Value("${twilio.alpha.metadata}")
	private String alphaMetadata;

	@Value("${twilio.alpha.list}")
	private String alphaList;

	@Value("${twilio.alpha.price}")
	private String alphaPrice;

	@Autowired
	private ISymbolRepo symbolRepo;

	@Override
	@Transactional
	public Symbol getById(String symbolId) {
		symbolId = symbolId.toUpperCase();
		return symbolRepo.findById(symbolId).orElse(getFromAlpha(symbolId));
	}

	@SuppressWarnings("rawtypes")
	@Transactional
	private Symbol getFromAlpha(String id) {
		try {
			RestTemplate restTemplate = new RestTemplate();
			String fooResourceUrl = alphaUrl + "/query?function=TIME_SERIES_INTRADAY&symbol=" + id + "&interval=5min&apikey=" + alphaKey;
			ResponseEntity<Map> response = restTemplate.getForEntity(fooResourceUrl, Map.class);

			// datos de cabecera
			Map body = response.getBody();
			// metada
			Map metadata = (Map) body.get(alphaMetadata);
			// ultimo registro
			String date = (String) metadata.get(alphaLastRefreshed);

			Map list = (Map) body.get(alphaList);

			Map obj = (Map) list.get(date);

			String price = (String) obj.get(alphaPrice);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateTime = new Date();
			dateTime = sdf.parse(date);
			Symbol symbol = new Symbol(id, Double.valueOf(price), dateTime);
			symbolRepo.save(symbol);
			return symbol;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Symbol();
	}

}
