package leCampusServer.repository;

import org.springframework.data.repository.CrudRepository;

import leCampusServer.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Integer> {

	Member findByName(String name);
}
