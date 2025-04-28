package cit.edu.zodifind.data

data class TarotCard(val name: String, val meaning: String)

object TarotDeck {
    val cards = listOf(
        // Major Arcana
        TarotCard("The Fool", "A new beginning, optimism, taking risks"),
        TarotCard("The Magician", "Manifestation, resourcefulness, skill"),
        TarotCard("The High Priestess", "Intuition, secrets revealed, inner wisdom"),
        TarotCard("The Empress", "Nurturing, abundance, fertility, motherhood"),
        TarotCard("The Emperor", "Authority, stability, control, structure"),
        TarotCard("The Hierophant", "Tradition, spiritual wisdom, guidance"),
        TarotCard("The Lovers", "Love, relationships, choices, unity"),
        TarotCard("The Chariot", "Victory, determination, control, willpower"),
        TarotCard("Strength", "Courage, inner strength, patience, confidence"),
        TarotCard("The Hermit", "Solitude, introspection, wisdom, guidance"),
        TarotCard("Wheel of Fortune", "Luck, destiny, cycles, turning point"),
        TarotCard("Justice", "Fairness, balance, truth, legal matters"),
        TarotCard("The Hanged Man", "Sacrifice, letting go, new perspectives"),
        TarotCard("Death", "Transformation, endings, rebirth"),
        TarotCard("Temperance", "Balance, moderation, patience, harmony"),
        TarotCard("The Devil", "Temptation, materialism, addiction, restriction"),
        TarotCard("The Tower", "Sudden upheaval, chaos, revelation, destruction"),
        TarotCard("The Star", "Hope, inspiration, spiritual clarity, guidance"),
        TarotCard("The Moon", "Illusion, intuition, dreams, subconscious"),
        TarotCard("The Sun", "Success, vitality, joy, clarity"),
        TarotCard("Judgement", "Judgement, awakening, reflection, renewal"),
        TarotCard("The World", "Completion, wholeness, accomplishment, travel"),

        // Minor Arcana
        // Wands
        TarotCard("Ace of Wands", "New beginnings, inspiration, opportunity"),
        TarotCard("Two of Wands", "Planning, decision-making, ambition"),
        TarotCard("Three of Wands", "Expansion, foresight, long-term plans"),
        TarotCard("Four of Wands", "Celebration, stability, harmony, homecoming"),
        TarotCard("Five of Wands", "Conflict, competition, disagreements"),
        TarotCard("Six of Wands", "Victory, recognition, success, achievement"),
        TarotCard("Seven of Wands", "Defensiveness, standing your ground"),
        TarotCard("Eight of Wands", "Swift action, progress, movement"),
        TarotCard("Nine of Wands", "Perseverance, resilience, guarding against challenges"),
        TarotCard("Ten of Wands", "Burden, responsibility, stress, overwork"),
        TarotCard("Page of Wands", "Curiosity, new ideas, exploration, ambition"),
        TarotCard("Knight of Wands", "Adventure, action, travel, impulsiveness"),
        TarotCard("Queen of Wands", "Confidence, leadership, determination, creativity"),
        TarotCard("King of Wands", "Leadership, vision, taking charge, strategic thinking"),

        // Cups
        TarotCard("Ace of Cups", "New emotions, love, compassion, creativity"),
        TarotCard("Two of Cups", "Partnership, unity, mutual attraction"),
        TarotCard("Three of Cups", "Celebration, friendship, community, joy"),
        TarotCard("Four of Cups", "Contemplation, apathy, dissatisfaction"),
        TarotCard("Five of Cups", "Loss, regret, emotional disappointment"),
        TarotCard("Six of Cups", "Nostalgia, childhood, past memories"),
        TarotCard("Seven of Cups", "Choices, illusion, dreams, wishful thinking"),
        TarotCard("Eight of Cups", "Leaving something behind, searching for meaning"),
        TarotCard("Nine of Cups", "Contentment, wish fulfillment, happiness"),
        TarotCard("Ten of Cups", "Emotional fulfillment, harmony, family happiness"),
        TarotCard("Page of Cups", "Creative beginnings, intuition, emotional messages"),
        TarotCard("Knight of Cups", "Romantic, idealistic, pursuing dreams"),
        TarotCard("Queen of Cups", "Compassion, nurturing, emotional balance"),
        TarotCard("King of Cups", "Emotional maturity, wisdom, diplomacy, compassion"),

        // Swords
        TarotCard("Ace of Swords", "New ideas, mental clarity, breakthroughs"),
        TarotCard("Two of Swords", "Indecision, blocked emotions, stalemate"),
        TarotCard("Three of Swords", "Heartbreak, emotional pain, sorrow"),
        TarotCard("Four of Swords", "Rest, recovery, meditation, retreat"),
        TarotCard("Five of Swords", "Conflict, defeat, dishonor, betrayal"),
        TarotCard("Six of Swords", "Transition, moving on, leaving behind troubles"),
        TarotCard("Seven of Swords", "Deception, secrecy, caution, trickery"),
        TarotCard("Eight of Swords", "Restriction, limitation, feeling trapped"),
        TarotCard("Nine of Swords", "Anxiety, fear, nightmares, regret"),
        TarotCard("Ten of Swords", "Betrayal, painful endings, rock bottom"),
        TarotCard("Page of Swords", "Curiosity, intellect, mental exploration"),
        TarotCard("Knight of Swords", "Action, aggression, decisiveness, speed"),
        TarotCard("Queen of Swords", "Clarity, perception, wisdom, communication"),
        TarotCard("King of Swords", "Leadership, intellect, strategy, authority"),

        // Pentacles
        TarotCard("Ace of Pentacles", "New financial beginnings, prosperity, stability"),
        TarotCard("Two of Pentacles", "Balance, adaptability, juggling priorities"),
        TarotCard("Three of Pentacles", "Collaboration, teamwork, skill development"),
        TarotCard("Four of Pentacles", "Security, possession, materialism, control"),
        TarotCard("Five of Pentacles", "Financial loss, poverty, hardship"),
        TarotCard("Six of Pentacles", "Generosity, charity, balance in giving/receiving"),
        TarotCard("Seven of Pentacles", "Investment, patience, long-term results"),
        TarotCard("Eight of Pentacles", "Hard work, skill-building, craftsmanship"),
        TarotCard("Nine of Pentacles", "Abundance, self-sufficiency, financial independence"),
        TarotCard("Ten of Pentacles", "Legacy, wealth, family, long-term stability"),
        TarotCard("Page of Pentacles", "Ambition, diligence, new opportunities"),
        TarotCard("Knight of Pentacles", "Hard work, routine, responsibility"),
        TarotCard("Queen of Pentacles", "Nurturing, practicality, abundance, security"),
        TarotCard("King of Pentacles", "Wealth, business, security, authority")
    )

    // Get a random tarot card from the deck
    fun getRandomCard(): TarotCard {
        return cards.random()
    }
}