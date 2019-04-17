package com.twilio.model.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(onConstructor_ = { @JsonCreator })
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@ToString
public class AlphaResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String symbolId;

	@Column
	private Double price;

	@Column
	private Date dateTime;
}
