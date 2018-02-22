package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.util.List;

import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.ListValue;

/**
 * @since 0.1.90
 * @version 0.1.90
 * @author Innoxia
 */
public class SlaveAuctionBidder {

	private String name;
	private Subspecies race;
	private Gender gender;
	private List<String> biddingComments;
	private List<String> failedBidComments;
	private List<String> successfulBidComments;
	
	public SlaveAuctionBidder(Subspecies race, Gender gender, List<String> biddingComments, List<String> failedBidComments, List<String> successfulBidComments) {
		super();
		this.race = race;
		this.gender = gender;
		this.biddingComments = biddingComments;
		this.failedBidComments = failedBidComments;
		this.successfulBidComments = successfulBidComments;
		
		if(gender.isFeminine()) {
			name = race.getSingularFemaleName();
		} else {
			name = race.getSingularMaleName();
		}
	}

	public String getName(boolean withDeterminer) {
		if(withDeterminer) {
			return UtilText.generateSingularDeterminer(name)+" "+name;
		}
		return name;
	}
	
	public Subspecies getRace() {
		return race;
	}

	public Gender getGender() {
		return gender;
	}

	public List<String> getBiddingComments() {
		return biddingComments;
	}

	public String getRandomBiddingComment() {
		return biddingComments.get(Util.random.nextInt(biddingComments.size()));
	}
	
	public List<String> getFailedBidComments() {
		return failedBidComments;
	}

	public String getRandomFailedBiddingComment() {
		return failedBidComments.get(Util.random.nextInt(failedBidComments.size()));
	}
	
	public List<String> getSuccessfulBidComments() {
		return successfulBidComments;
	}
	
	public String getRandomSuccessfulBiddingComment() {
		return successfulBidComments.get(Util.random.nextInt(successfulBidComments.size()));
	}
	
	public static SlaveAuctionBidder generateNewSlaveAuctionBidder(NPC slave) {
		
		Subspecies[] races = new Subspecies[] {Subspecies.CAT_MORPH, Subspecies.COW_MORPH, Subspecies.DEMON, Subspecies.DOG_MORPH, Subspecies.HARPY, Subspecies.HORSE_MORPH, Subspecies.HUMAN, Subspecies.SQUIRREL_MORPH, Subspecies.WOLF_MORPH};
		
		// I did consider basing gender on slave's preferences, so that players who export their own character aren't turned-off by the fact their character is being sold to a gender they don't like, but I figured that maybe some people are into that too...
		Gender[] genders = new Gender[] {Gender.F_V_B_FEMALE, Gender.F_P_V_B_FUTANARI, Gender.M_P_MALE};
		
		Subspecies race = races[Util.random.nextInt(races.length)];
		Gender gender = genders[Util.random.nextInt(genders.length)];
		
		List<String> biddingComments = Util.newArrayListOfValues(
				new ListValue<>("I deserve a new fucktoy..."),
				new ListValue<>("My slaves need a new toy..."),
				new ListValue<>(UtilText.parse(slave, "I could put [npc.herHim] to work in the brothel...")),
				new ListValue<>(UtilText.parse(slave, "I could put [npc.herHim] to work in the milking sheds...")),
				new ListValue<>(UtilText.parse(slave, "[npc.She] looks like [npc.she]'d make a good maid...")));
		
		List<String> failedBidComments = Util.newArrayListOfValues(
				new ListValue<>("I can't afford that..."),
				new ListValue<>("That's too much for me..."),
				new ListValue<>("Maybe I'll bid on the next one..."));
		
		List<String> successfulBidComments = Util.newArrayListOfValues(
				new ListValue<>(UtilText.parse(slave, "I'm going to break [npc.herHim] in as soon as I get home...")),
				new ListValue<>(UtilText.parse(slave, "I'll get my other slaves to break [npc.herHim] in...")),
				new ListValue<>(UtilText.parse(slave, "I'm sure [npc.she]'ll love [npc.her] new life in my brothel...")),
				new ListValue<>(UtilText.parse(slave, "I'm sure [npc.she]'ll love [npc.her] new life in the milking sheds...")));
		
		return new SlaveAuctionBidder(race, gender, biddingComments, failedBidComments, successfulBidComments);
	}
}
