package it.macke.ee7.api.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.macke.ee7.minimal.domain.Talk;
import it.macke.ee7.minimal.persistence.TalkRepository;

@Path("/talks")
public class TalkResource
{
	@Inject
	private TalkRepository repo;

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Talk> allTalks()
	{
		return repo.findAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Talk findTalk(@PathParam("id") final String id)
	{
		final Optional<Talk> talk = repo.find(id);
		if (!talk.isPresent())
		{
			throw new NotFoundException();
		}
		return talk.get();
	}
}
