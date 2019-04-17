package com.twilio.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.model.Symbol;
import com.twilio.service.ISymbolSrv;

@RestController
@RequestMapping("/api/symbol")
@CrossOrigin
public class SymbolApi {

	@Autowired
	private ISymbolSrv symbolSrv;

	@GetMapping("/{symbolId}")
	@ResponseBody
	public ResponseEntity<Symbol> getById(@PathVariable String symbolId) {
		return new ResponseEntity<Symbol>(symbolSrv.getById(symbolId), HttpStatus.OK);
	}
}
