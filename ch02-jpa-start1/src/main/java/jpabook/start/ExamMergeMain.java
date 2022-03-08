package jpabook.start;

import javax.persistence.*;

public class ExamMergeMain {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
	
	public static void main(String[] args) {
		
		Member member = createMember("memberA", "회원1");
		
		member.setUsername("회원명 변경");
		
		mergeMember(member);
	}
	
	static Member createMember(String id ,String username) {
		EntityManager em1 = emf.createEntityManager();
		EntityTransaction tx1 = em1.getTransaction();
		tx1.begin();
		
		Member member = new Member();
		member.setId(id);
		member.setAge(33);
		member.setUsername(username);
		
		em1.persist(member);
		tx1.commit();
		
		em1.close();
		
		return member;
	}
	
	static void mergeMember(Member member) {
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		
		tx2.begin();
		Member mergeMember = em2.merge(member);
		tx2.commit();
		
		System.out.println("member = " + member.getUsername());
		
		System.out.println("mergeMember = " + mergeMember.getUsername());
		
		System.out.println("em2 contains member = " + em2.contains(member));
		
		System.out.println("em2 contains mergeMember = " + em2.contains(mergeMember));
		
		em2.close();
	}
}
