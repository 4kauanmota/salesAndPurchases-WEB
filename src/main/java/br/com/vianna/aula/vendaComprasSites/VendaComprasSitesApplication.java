package br.com.vianna.aula.vendaComprasSites;

import br.com.vianna.aula.vendaComprasSites.model.*;
import br.com.vianna.aula.vendaComprasSites.model.dao.*;
import br.com.vianna.aula.vendaComprasSites.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VendaComprasSitesApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(VendaComprasSitesApplication.class, args);
	}

	@Autowired
	ClientService cs;

	@Autowired
	AdminsDAO admin;

	@Autowired
	EvaluationsDAO evaluations;

	@Autowired
	ProjectsDAO projects;

	@Autowired
	RulesDAO rules;

	@Autowired
	SpecsDAO specs;

	@Autowired
	CirculationsDAO circulations;

	@Autowired
	PasswordEncoder password;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Specs s1 = new Specs(0, "CSS", new ArrayList<>());
		Specs s2 = new Specs(0, "HTML", new ArrayList<>());
		Specs s3 = new Specs(0, "JAVASCRIPT", new ArrayList<>());
		Specs s4 = new Specs(0, "JAVA", new ArrayList<>());
		Specs s5 = new Specs(0, "C#", new ArrayList<>());
		Specs s6 = new Specs(0, "PHP", new ArrayList<>());

		specs.save(s1);
		specs.save(s2);
		specs.save(s3);
		specs.save(s4);
		specs.save(s5);
		specs.save(s6);

		Evaluations ev1 = new Evaluations(0, 4, new Date(), null);
		Evaluations ev2 = new Evaluations(0, 8, new Date(), null);

		evaluations.save(ev1);
		evaluations.save(ev2);

		List<Evaluations> evaluationsList = new ArrayList<>();

		evaluationsList.add(ev1);
		evaluationsList.add(ev2);

		Clients c = Clients.builder()
				.id(0)
				.name("Liana")
				.email("4lianamota@gmail.com")
				.login("4lianamota")
				.password(password.encode("123"))
				.lastAcessDate(new Date())
				.projects(new ArrayList<>())
				.profilePicture("https://evorastudio.com.br/wp-content/uploads/2018/10/LE1A4840-e1595429186379.jpg")
				.balance(0)
				.circulations(new ArrayList<>())
				.evaluations(evaluationsList)
				.build();

		cs.save(c);

		Projects p1 = Projects.builder()
				.id(0)
				.title("Manda Trends")
				.description("A newsletter site made by Kauan Soares Mota for an inteview, bla bla bla bla bla bla bla bla bla bla bla. Bla bla bla bla bla bla")
				.cost(500)
				.link("https://4kauanmota.github.io/mandarin_mandaTrends/")
				.thumbnail("https://mandarin.com.br/logo_mandarin.png")
				.specs(new ArrayList<>())
				.lastUpdate(new Date())
				.isOpen(true)
				.seller(c)
				.circulations(null)
				.build();

		p1.getSpecs().add(s1);
		p1.getSpecs().add(s2);

		projects.save(p1);

		s1.getProjects().add(p1);
		s2.getProjects().add(p1);

		specs.save(s1);
		specs.save(s2);

		c.getProjects().add(p1);

		cs.save(c);

		Admins a = Admins.builder()
				.id(1)
				.name("Kauan")
				.email("4kauanmota@gmail.com")
				.login("4kauanmota")
				.password(password.encode("123"))
				.lastAcessDate(new Date())
				.profilePicture("https://st2.depositphotos.com/2249091/10230/i/450/depositphotos_102307138-stock-photo-talking-with-a-future-worker.jpg")
				.admNumber(1)
				.addedRules(1)
				.deletedProjects(0)
				.build();

		admin.save(a);

		Rules r1 = Rules.builder()
				.id(0)
				.title("Do not copy post")
				.description("Please, this is not cool :(")
				.build();

		rules.save(r1);
	}
}
