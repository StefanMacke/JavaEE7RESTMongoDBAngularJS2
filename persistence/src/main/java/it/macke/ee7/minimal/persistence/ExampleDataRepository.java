package it.macke.ee7.minimal.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import it.macke.ee7.minimal.domain.Attendee;
import it.macke.ee7.minimal.domain.Talk;

@ApplicationScoped
public class ExampleDataRepository
{
	@PersistenceContext
	private EntityManager em;

	@Transactional
			void addExampleData(@Observes @Initialized(ApplicationScoped.class) final Object event)
	{
		try
		{
			try
			{
				if (em.createQuery("SELECT COUNT(a) FROM Attendee a", Long.class).getSingleResult() == 0)
				{
					persistExampleData();
				}
			}
			catch (final Exception e)
			{
				persistExampleData();
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	private void persistExampleData()
	{
		final Attendee stefan = new Attendee("Stefan", "Macke");
		final Attendee adam = new Attendee("Adam", "Bien");
		final Attendee arun = new Attendee("Arun", "Gupta");

		final Talk java1 = new Talk("Java EE 7", "Fun with Java EE 7");
		final Talk java2 = new Talk("More Java EE 7", "More Fun with Java EE 7");
		final Talk java3 = new Talk("Even More Java EE 7", "Even More Fun with Java EE 7");

		stefan.attend(java1);
		stefan.attend(java2);
		stefan.attend(java3);

		adam.attend(java2);
		adam.attend(java3);

		arun.attend(java3);

		em.persist(stefan);
		em.persist(adam);
		em.persist(arun);
		em.persist(java1);
		em.persist(java2);
		em.persist(java3);
	}
}
