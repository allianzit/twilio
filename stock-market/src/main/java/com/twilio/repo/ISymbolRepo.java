package com.twilio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.twilio.model.Symbol;

@Repository
public interface ISymbolRepo extends JpaRepository<Symbol, String> {

}
