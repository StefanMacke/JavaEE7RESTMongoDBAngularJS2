package it.macke.ee7.api.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.macke.ee7.minimal.domain.Attendee;
import it.macke.ee7.minimal.persistence.AttendeeRepository;

@Path("/attendees")
public class AttendeeResource
{
	@Inject
	private AttendeeRepository repo;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Attendee> allTalks()
	{
		return repo.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Attendee findAttendee(@PathParam("id") final String id)
	{
		final Optional<Attendee> attendee = repo.find(id);
		if (!attendee.isPresent())
		{
			throw new NotFoundException();
		}
		return attendee.get();
	}
}
