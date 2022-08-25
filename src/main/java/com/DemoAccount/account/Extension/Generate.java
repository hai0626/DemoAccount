package com.DemoAccount.account.Extension;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.DemoAccount.account.Repository.AccountRepository;

public class Generate implements IdentifierGenerator{
	private String prefix = "ACC";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {

		String query = "SELECT a.AgentCode FROM Account a";
		Stream<String> ids = session.createQuery(query, String.class).stream();
		Long max = ids.map(o -> o.replace(prefix, "")).mapToLong(Long::parseLong).max().orElse(0L);
		return prefix + (String.format("%05d", max + 1));

	}
}
