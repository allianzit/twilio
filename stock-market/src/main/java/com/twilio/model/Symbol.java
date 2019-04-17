package com.twilio.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@Data
@NoArgsConstructor(onConstructor_ = { @JsonCreator })
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@ToString
public class Symbol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String symbolId;

	@Column 
	private Double price;

	@Column
	private Date dateTime;
}
