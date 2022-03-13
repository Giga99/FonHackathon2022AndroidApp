package fon.hakaton.fonhakatonandroidapp.presentation.tips

import fon.hakaton.fonhakatonandroidapp.domain.models.TipModel

sealed class TipsIntent {
    object ErrorDialogDismissed : TipsIntent()
    object BackClicked : TipsIntent()
    object NextClicked : TipsIntent()
    object PreviousClicked : TipsIntent()
}

sealed class TipsSideEffect {
    data class ShowMessage(val text: String) : TipsSideEffect()
}

data class TipsViewState(
    val tips: List<TipModel> = listOf(
        TipModel(
            title = "Cold water for washing clothes",
            description = "90 percent of the electricity used to wash a load goes toward heating the water. \n" +
                    "You don’t have to worry about your clothes staying dirty since most detergents are designed to work better in cold water anyways.\n"
        ),
        TipModel(
            title = "Standby power draw",
            description = "Despite being “switched off” almost all electrical devices continue operate in a standby mode and continue using electricity even when they’re not in active use. This standby power draw accounts for about 10% of an average household's annual electricity use. Unplug your appliances or turn them off at the socket to reduce your monthly carbon footprint by x%."
        ),
        TipModel(
            title = "Use LED lightbulbs",
            description = "LED lightbulbs are significantly more efficient than their incandescent counterparts. They use 75% less energy and last 25 times longer!"
        ),
        TipModel(
            title = "Air dry your clothes",
            description = "Consider air drying your clothes instead of using a dryer. The California Energy Commission says that dryers use approximately 6 percent of a home's total electricity usage."
        ),
        TipModel(
            title = "Beware of toilet leakage",
            description = "Check your toilet tank for leaks. Put a few drops of food coloring in your toilet tank. If, without flushing, the coloring begins to appear in the bowl, you have a leak that may be wasting more than 100 gallons of water a day."
        ),
        TipModel(
            title = "Use a dishwasher",
            description = "Dishwashers may use electricity, but they save more money, water and time than hand washing. Using a water-saving dishwasher instead of hand washing can save you, on average, 2,000 liters of water per month"
        ),
        TipModel(
            title = "Install an aerator",
            description = "An aerator is a device which is attached to faucets\n" +
                    "\n" +
                    "Dishwashers may use electricity, but they save more energy, money, water and time than hand washing. According to the California Energy Commission, using an Energy Star-qualified dishwasher instead of hand washing can save you, on average, 5,000 gallons of water and \$40 in utility costs each year, not to mention 230 hours of your time."
        ),
        TipModel(
            title = "Water Saving Aerators/ Perlatori",
            description = "Baterije za lavabo koje ne štede vodu i koje imaju neadekvatne perlatore imaju protok od 13 l/min. Ugradnjom DOMING-ovog perlatora dobijamo protok od samo 4 l/min, bez umanjenja komfora prilikom upotrebe. Dodavanjem vazduha i uz pomoć posebnog graničnika protoka. Ugradnjom perlatora omogućuje se povećani dotok vazduha, pa kao rezultat imamo bogati, penušavi mlaz vode. Precizni elastični graničnik automatski reaguje na različite pritiske vode menjajući svoj oblik. On ograničava protok i tako neprestano štedi pitku vodu."
        ),
        TipModel(
            title = "General Advice",
            description = "Data shows that food production is responsible for 25% of the world’s greenhouse gas emissions. Surprisingly, food transport is a small contributor accounting for less than 10% of these emissions. Eating less meat, specifically less beef will reduce your carbon footprint significantly more than buying locally produced food. https://ourworldindata.org/food-ghg-emissions\n"
        ),
        TipModel(
            title = "The problem with beef",
            description = "Beef farming produces almost 10 times more greenhouse gas emissions than pig or poultry farming. This is because cows are ruminant animals whose natural digestive processes produce large amounts of methane. Cutting back on beef and dairy products can immensely reduce your carbon footprint.\n" +
                    "https://letstalkscience.ca/educational-resources/stem-in-context/cows-methane-and-climate-change\n"
        ),
        TipModel(
            title = "Projekat “Eko-kesa za čistiji grad“\n",
            description = "JKP “Gradska čistoća” već dugi niz godina uspešno sprovodi akciju pod nazivom “Eko-kesa za čistiji grad“. Učesnici akcije u svojim domaćinstvima u “eko-kesu” zapremine 120 litara odlažu PET i MET ambalažu i ostvaruju pravo na popust od 20% popusta na infostan."
        ),
    ),
    val currentIndex: Int = 0,
)
