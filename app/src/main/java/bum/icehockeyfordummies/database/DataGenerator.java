package bum.icehockeyfordummies.database;

import java.util.ArrayList;
import java.util.List;


// Generate all based data
public class DataGenerator {

    // Leagues
    public static List<LeagueEntity> generateLeagues() {
        List<LeagueEntity> leagues = new ArrayList<>();

        // Id = 1
        LeagueEntity national = new LeagueEntity();
        national.setNameLeague("National League");
        national.setLogoLeague("national_league.jpg");
        national.setSystemLeague(true);

        // Id = 2
        LeagueEntity swiss = new LeagueEntity();
        swiss.setNameLeague("Swiss League");
        swiss.setLogoLeague("swiss_league.jpg");
        swiss.setSystemLeague(true);

        // Id = 3
        LeagueEntity mysports = new LeagueEntity();
        mysports.setNameLeague("MySports League");
        mysports.setLogoLeague("mysports_league.jpg");
        mysports.setSystemLeague(true);

        // Id = 4
        LeagueEntity regio = new LeagueEntity();
        regio.setNameLeague("Regio League");
        regio.setLogoLeague("regio_league.png");
        regio.setSystemLeague(true);


        leagues.add(national);
        leagues.add(swiss);
        leagues.add(mysports);
        leagues.add(regio);

        return leagues;
    }


    // Clubs
    public static List<ClubEntity> generateClubs() {
        List<ClubEntity> clubs = new ArrayList<>();

        // Id = 1 (National League)
        ClubEntity fribourg = new ClubEntity();
        fribourg.setNameClub("Fribourg-Gottéron");
        fribourg.setLogoClub("fribourg_gotteron.png");
        fribourg.setFK_idLeague(1);
        fribourg.setSystemClub(true);
        fribourg.setFavoriteClub(false);

        // Id = 2 (National League)
        ClubEntity scBern = new ClubEntity();
        scBern.setNameClub("SC Bern");
        scBern.setLogoClub("sc_bern.png");
        scBern.setFK_idLeague(1);
        scBern.setSystemClub(true);
        scBern.setFavoriteClub(false);

        clubs.add(fribourg);
        clubs.add(scBern);


        // Id = 3 (Swiss League)
        ClubEntity ehcOlten = new ClubEntity();
        ehcOlten.setNameClub("EHC Olten");
        ehcOlten.setLogoClub("ehc_olten.png");
        ehcOlten.setFK_idLeague(2);
        ehcOlten.setSystemClub(true);
        ehcOlten.setFavoriteClub(false);

        // Id = 4 (Swiss League)
        ClubEntity ehcVisp = new ClubEntity();
        ehcVisp.setNameClub("EHC Visp");
        ehcVisp.setLogoClub("ehc_visp.png");
        ehcVisp.setFK_idLeague(2);
        ehcVisp.setSystemClub(true);
        ehcVisp.setFavoriteClub(false);

        clubs.add(ehcOlten);
        clubs.add(ehcVisp);


        // Id = 5 (MySports League)
        ClubEntity hcValais = new ClubEntity();
        hcValais.setNameClub("HC Valais-Chablais");
        hcValais.setLogoClub("hcvalais.png");
        hcValais.setFK_idLeague(3);
        hcValais.setSystemClub(true);
        hcValais.setFavoriteClub(false);

        // Id = 6 (MySports League)
        ClubEntity hcSierre = new ClubEntity();
        hcSierre.setNameClub("HC Sierre");
        hcSierre.setLogoClub("hcsierre.png");
        hcSierre.setFK_idLeague(3);
        hcSierre.setSystemClub(true);
        hcSierre.setFavoriteClub(false);

        clubs.add(hcValais);
        clubs.add(hcSierre);


        // Id = 7 (Regio League)
        ClubEntity hcMoutier = new ClubEntity();
        hcMoutier.setNameClub("HC Moutier");
        hcMoutier.setLogoClub("hc_moutier.png");
        hcMoutier.setFK_idLeague(4);
        hcMoutier.setSystemClub(true);
        hcMoutier.setFavoriteClub(false);

        // Id = 8 (Regio League)
        ClubEntity hcPrilly = new ClubEntity();
        hcPrilly.setNameClub("HC Prilly Black Panthers");
        hcPrilly.setLogoClub("hc_prilly.png");
        hcPrilly.setFK_idLeague(4);
        hcPrilly.setSystemClub(true);
        hcPrilly.setFavoriteClub(false);

        clubs.add(hcMoutier);
        clubs.add(hcPrilly);


        return clubs;
    }


    // Players
    public static List<PlayerEntity> generatePlayers() {
        List<PlayerEntity> players = new ArrayList<>();

        // Fribourg-Gottéron (1)
        // 1 - Reto Berra #20
        PlayerEntity berra = new PlayerEntity();
        berra.setNumberPlayer(20);
        berra.setFirstNamePlayer("Reto");
        berra.setLastNamePlayer("Berra");
        berra.setBirthdatePlayer(1987);
        berra.setPicturePlayer("berra.jpg");
        berra.setPositionPlayer("Goalie");
        berra.setLicensePlayer("CH");
        berra.setPointsPlayer(0);
        berra.setFK_idClub(1);
        berra.setSystemPlayer(true);

        // 2 - Marc Abplanalp #2
        PlayerEntity abplanalp = new PlayerEntity();
        abplanalp.setNumberPlayer(2);
        abplanalp.setFirstNamePlayer("Marc");
        abplanalp.setLastNamePlayer("Abplanalp");
        abplanalp.setBirthdatePlayer(1984);
        abplanalp.setPicturePlayer("abplanalp.jpg");
        abplanalp.setPositionPlayer("Defense");
        abplanalp.setLicensePlayer("CH");
        abplanalp.setPointsPlayer(3);
        abplanalp.setFK_idClub(1);
        abplanalp.setSystemPlayer(true);

        // 3 - Marco Forrer #33
        PlayerEntity forrer = new PlayerEntity();
        forrer.setNumberPlayer(33);
        forrer.setFirstNamePlayer("Marco");
        forrer.setLastNamePlayer("Forrer");
        forrer.setBirthdatePlayer(1996);
        forrer.setPicturePlayer("forrer.jpg");
        forrer.setPositionPlayer("Defense");
        forrer.setLicensePlayer("CH");
        forrer.setPointsPlayer(0);
        forrer.setFK_idClub(1);
        forrer.setSystemPlayer(true);

        // 4 - Lukas Lhotak #11
        PlayerEntity lhotak = new PlayerEntity();
        lhotak.setNumberPlayer(11);
        lhotak.setFirstNamePlayer("Lukas");
        lhotak.setLastNamePlayer("Lhotak");
        lhotak.setBirthdatePlayer(1993);
        lhotak.setPicturePlayer("lhotak.jpg");
        lhotak.setPositionPlayer("Winger");
        lhotak.setLicensePlayer("CH");
        lhotak.setPointsPlayer(6);
        lhotak.setFK_idClub(1);
        lhotak.setSystemPlayer(true);

        // 5 - Michal Birner #16
        PlayerEntity birner = new PlayerEntity();
        birner.setNumberPlayer(16);
        birner.setFirstNamePlayer("Michal");
        birner.setLastNamePlayer("Birner");
        birner.setBirthdatePlayer(1986);
        birner.setPicturePlayer("birner.jpg");
        birner.setPositionPlayer("Winger");
        birner.setLicensePlayer("Foreign");
        birner.setPointsPlayer(3);
        birner.setFK_idClub(1);
        birner.setSystemPlayer(true);

        players.add(berra);
        players.add(abplanalp);
        players.add(forrer);
        players.add(lhotak);
        players.add(birner);


        // SC Bern (2)
        // 6 - Leonardo Genoni #30
        PlayerEntity genoni = new PlayerEntity();
        genoni.setNumberPlayer(30);
        genoni.setFirstNamePlayer("Leonardo");
        genoni.setLastNamePlayer("Genoni");
        genoni.setBirthdatePlayer(1987);
        genoni.setPicturePlayer("genoni.jpg");
        genoni.setPositionPlayer("Goalie");
        genoni.setLicensePlayer("CH");
        genoni.setPointsPlayer(0);
        genoni.setFK_idClub(2);
        genoni.setSystemPlayer(true);

        // 7 - Beat Gerber #2
        PlayerEntity gerber = new PlayerEntity();
        gerber.setNumberPlayer(2);
        gerber.setFirstNamePlayer("Beat");
        gerber.setLastNamePlayer("Gerber");
        gerber.setBirthdatePlayer(1982);
        gerber.setPicturePlayer("gerber.jpg");
        genoni.setPositionPlayer("Defense");
        gerber.setLicensePlayer("CH");
        gerber.setPointsPlayer(1);
        gerber.setFK_idClub(2);
        gerber.setSystemPlayer(true);

        // 8 - Yanik Burren #77
        PlayerEntity burren = new PlayerEntity();
        burren.setNumberPlayer(77);
        burren.setFirstNamePlayer("Yanik");
        burren.setLastNamePlayer("Burren");
        burren.setBirthdatePlayer(1997);
        burren.setPicturePlayer("burren.jpg");
        burren.setPositionPlayer("Defense");
        burren.setLicensePlayer("CH");
        burren.setPointsPlayer(4);
        burren.setFK_idClub(2);
        burren.setSystemPlayer(true);

        // 9 - Jan Mursak #9
        PlayerEntity mursak = new PlayerEntity();
        mursak.setNumberPlayer(9);
        mursak.setFirstNamePlayer("Jan");
        mursak.setLastNamePlayer("Mursak");
        mursak.setBirthdatePlayer(1988);
        mursak.setPicturePlayer("mursak.jpg");
        mursak.setPositionPlayer("Winger");
        mursak.setLicensePlayer("Foreign");
        mursak.setPointsPlayer(13);
        mursak.setFK_idClub(2);
        mursak.setSystemPlayer(true);

        // 10 - Simon Moser #21
        PlayerEntity moser = new PlayerEntity();
        moser.setNumberPlayer(21);
        moser.setFirstNamePlayer("Simon");
        moser.setLastNamePlayer("Moser");
        moser.setBirthdatePlayer(1989);
        moser.setPicturePlayer("moser.jpg");
        moser.setPositionPlayer("Winger");
        moser.setLicensePlayer("CH");
        moser.setPointsPlayer(8);
        moser.setFK_idClub(2);
        moser.setSystemPlayer(true);

        players.add(genoni);
        players.add(gerber);
        players.add(burren);
        players.add(mursak);
        players.add(moser);


        // EHC Olten (3)
        // 11 - Matthias Mischler #35
        PlayerEntity mischler = new PlayerEntity();
        mischler.setNumberPlayer(35);
        mischler.setFirstNamePlayer("Matthias");
        mischler.setLastNamePlayer("Mischler");
        mischler.setBirthdatePlayer(1990);
        mischler.setPicturePlayer("mischler.jpg");
        mischler.setPositionPlayer("Goalie");
        mischler.setLicensePlayer("CH");
        mischler.setPointsPlayer(0);
        mischler.setFK_idClub(3);
        mischler.setSystemPlayer(true);

        // 12 - Simon Lüthi #8
        PlayerEntity luethi = new PlayerEntity();
        luethi.setNumberPlayer(8);
        luethi.setFirstNamePlayer("Simon");
        luethi.setLastNamePlayer("Lüthi");
        luethi.setBirthdatePlayer(1986);
        luethi.setPicturePlayer("luethi.jpg");
        luethi.setPositionPlayer("Defense");
        luethi.setLicensePlayer("CH");
        luethi.setPointsPlayer(3);
        luethi.setFK_idClub(3);
        luethi.setSystemPlayer(true);

        // 13 - Tim Bucher #19
        PlayerEntity bucher = new PlayerEntity();
        bucher.setNumberPlayer(19);
        bucher.setFirstNamePlayer("Tim");
        bucher.setLastNamePlayer("Bucher");
        bucher.setBirthdatePlayer(1988);
        bucher.setPicturePlayer("bucher.jpg");
        bucher.setPositionPlayer("Defense");
        bucher.setLicensePlayer("CH");
        bucher.setPointsPlayer(1);
        bucher.setFK_idClub(3);
        bucher.setSystemPlayer(true);

        // 14 - Cason Hohmann #7
        PlayerEntity hohmann = new PlayerEntity();
        hohmann.setNumberPlayer(7);
        hohmann.setFirstNamePlayer("Cason");
        hohmann.setLastNamePlayer("Hohmann");
        hohmann.setBirthdatePlayer(1993);
        hohmann.setPicturePlayer("hohmann.jpg");
        hohmann.setPositionPlayer("Winger");
        hohmann.setLicensePlayer("Foreign");
        hohmann.setPointsPlayer(17);
        hohmann.setFK_idClub(3);
        hohmann.setSystemPlayer(true);

        // 15 - Ueli Huber #10
        PlayerEntity huber = new PlayerEntity();
        huber.setNumberPlayer(10);
        huber.setFirstNamePlayer("Ueli");
        huber.setLastNamePlayer("Huber");
        huber.setBirthdatePlayer(1995);
        huber.setPicturePlayer("huber.jpg");
        huber.setPositionPlayer("Winger");
        huber.setLicensePlayer("CH");
        huber.setPointsPlayer(4);
        huber.setFK_idClub(3);
        huber.setSystemPlayer(true);

        players.add(mischler);
        players.add(luethi);
        players.add(bucher);
        players.add(hohmann);
        players.add(huber);


        // EHC Visp (4)
        // 16 - Reto Lory #26
        PlayerEntity lory = new PlayerEntity();
        lory.setNumberPlayer(26);
        lory.setFirstNamePlayer("Reto");
        lory.setLastNamePlayer("Lory");
        lory.setBirthdatePlayer(1988);
        lory.setPicturePlayer("lory.jpg");
        lory.setPositionPlayer("Goalie");
        lory.setLicensePlayer("CH");
        lory.setPointsPlayer(0);
        lory.setFK_idClub(4);
        lory.setSystemPlayer(true);

        // 17 - Jens Nater #17
        PlayerEntity nater = new PlayerEntity();
        nater.setNumberPlayer(17);
        nater.setFirstNamePlayer("Jens");
        nater.setLastNamePlayer("Nater");
        nater.setBirthdatePlayer(1995);
        nater.setPicturePlayer("nater.jpg");
        nater.setPositionPlayer("Defense");
        nater.setLicensePlayer("CH");
        nater.setPointsPlayer(4);
        nater.setFK_idClub(4);
        nater.setSystemPlayer(true);

        // 18 - Marc Steiner #27
        PlayerEntity steiner = new PlayerEntity();
        steiner.setNumberPlayer(27);
        steiner.setFirstNamePlayer("Marc");
        steiner.setLastNamePlayer("Steiner");
        steiner.setBirthdatePlayer(1996);
        steiner.setPicturePlayer("steiner.jpg");
        steiner.setPositionPlayer("Defense");
        steiner.setLicensePlayer("CH");
        steiner.setPointsPlayer(4);
        steiner.setFK_idClub(4);
        steiner.setSystemPlayer(true);

        // 19 - Oliver Achermann #9
        PlayerEntity achermann = new PlayerEntity();
        achermann.setNumberPlayer(9);
        achermann.setFirstNamePlayer("Oliver");
        achermann.setLastNamePlayer("Achermann");
        achermann.setBirthdatePlayer(1994);
        achermann.setPicturePlayer("");
        achermann.setPositionPlayer("Winger");
        achermann.setLicensePlayer("CH");
        achermann.setPointsPlayer(8);
        achermann.setFK_idClub(4);
        achermann.setSystemPlayer(true);

        // 20 - Niki Altorfer #13
        PlayerEntity altorfer = new PlayerEntity();
        altorfer.setNumberPlayer(13);
        altorfer.setFirstNamePlayer("Niki");
        altorfer.setLastNamePlayer("Altorfer");
        altorfer.setBirthdatePlayer(1990);
        altorfer.setPicturePlayer("altorfer.jpg");
        altorfer.setPositionPlayer("Winger");
        altorfer.setLicensePlayer("CH");
        altorfer.setPointsPlayer(3);
        altorfer.setFK_idClub(4);
        altorfer.setSystemPlayer(true);

        players.add(lory);
        players.add(nater);
        players.add(steiner);
        players.add(achermann);
        players.add(altorfer);


        // HC Valais-Chablais (5)
        // 21 - Maxime Baud #82
        PlayerEntity baud = new PlayerEntity();
        baud.setNumberPlayer(82);
        baud.setFirstNamePlayer("Maxime");
        baud.setLastNamePlayer("Baud");
        baud.setBirthdatePlayer(1997);
        baud.setPicturePlayer("baud.jpg");
        baud.setPositionPlayer("Goalie");
        baud.setLicensePlayer("CH");
        baud.setPointsPlayer(0);
        baud.setFK_idClub(5);
        baud.setSystemPlayer(true);

        // 22 - Jimmy Oudelet #13
        PlayerEntity oudelet = new PlayerEntity();
        oudelet.setNumberPlayer(13);
        oudelet.setFirstNamePlayer("Jimmy");
        oudelet.setLastNamePlayer("Oudelet");
        oudelet.setBirthdatePlayer(1992);
        oudelet.setPicturePlayer("");
        oudelet.setPositionPlayer("Defense");
        oudelet.setLicensePlayer("CH");
        oudelet.setPointsPlayer(3);
        oudelet.setFK_idClub(5);
        oudelet.setSystemPlayer(true);

        // 23 - Thomas Cheseaux #47
        PlayerEntity cheseaux = new PlayerEntity();
        cheseaux.setNumberPlayer(47);
        cheseaux.setFirstNamePlayer("Thomas");
        cheseaux.setLastNamePlayer("Cheseaux");
        cheseaux.setBirthdatePlayer(1995);
        cheseaux.setPicturePlayer("");
        cheseaux.setPositionPlayer("Defense");
        cheseaux.setLicensePlayer("CH");
        cheseaux.setPointsPlayer(7);
        cheseaux.setFK_idClub(5);
        cheseaux.setSystemPlayer(true);

        // 24 - Jérémy Gailland #18
        PlayerEntity gailland = new PlayerEntity();
        gailland.setNumberPlayer(18);
        gailland.setFirstNamePlayer("Jérémy");
        gailland.setLastNamePlayer("Gailland");
        gailland.setBirthdatePlayer(1988);
        gailland.setPicturePlayer("");
        gailland.setPositionPlayer("Winger");
        gailland.setLicensePlayer("CH");
        gailland.setPointsPlayer(25);
        gailland.setFK_idClub(5);
        gailland.setSystemPlayer(true);

        // 25 - Romain Seydoux #21
        PlayerEntity seydoux = new PlayerEntity();
        seydoux.setNumberPlayer(21);
        seydoux.setFirstNamePlayer("Romain");
        seydoux.setLastNamePlayer("Seydoux");
        seydoux.setBirthdatePlayer(1994);
        seydoux.setPicturePlayer("");
        seydoux.setPositionPlayer("Winger");
        seydoux.setLicensePlayer("CH");
        seydoux.setPointsPlayer(24);
        seydoux.setFK_idClub(5);
        seydoux.setSystemPlayer(true);

        players.add(baud);
        players.add(oudelet);
        players.add(cheseaux);
        players.add(gailland);
        players.add(seydoux);


        // HC Sierre (6)
        // 26 - Remo Giovannini #30
        PlayerEntity giovannini = new PlayerEntity();
        giovannini.setNumberPlayer(30);
        giovannini.setFirstNamePlayer("Remo");
        giovannini.setLastNamePlayer("Giovannini");
        giovannini.setBirthdatePlayer(1991);
        giovannini.setPicturePlayer("giovannini.jpg");
        giovannini.setPositionPlayer("Goalie");
        giovannini.setLicensePlayer("CH");
        giovannini.setPointsPlayer(0);
        giovannini.setFK_idClub(6);
        giovannini.setSystemPlayer(true);

        // 27 - Julien Massy #4
        PlayerEntity massy = new PlayerEntity();
        massy.setNumberPlayer(4);
        massy.setFirstNamePlayer("Julien");
        massy.setLastNamePlayer("Massy");
        massy.setBirthdatePlayer(1998);
        massy.setPicturePlayer("massy.jpg");
        massy.setPositionPlayer("Defense");
        massy.setLicensePlayer("CH");
        massy.setPointsPlayer(0);
        massy.setFK_idClub(6);
        massy.setSystemPlayer(true);

        // 28 - Eliott Meyrat #7
        PlayerEntity meyrat = new PlayerEntity();
        meyrat.setNumberPlayer(7);
        meyrat.setFirstNamePlayer("Eliott");
        meyrat.setLastNamePlayer("Meyrat");
        meyrat.setBirthdatePlayer(1994);
        meyrat.setPicturePlayer("meyrat.jpg");
        meyrat.setPositionPlayer("Defense");
        meyrat.setLicensePlayer("CH");
        meyrat.setPointsPlayer(6);
        meyrat.setFK_idClub(6);
        meyrat.setSystemPlayer(true);

        // 29 - Romain Wyssen #8
        PlayerEntity wyssen = new PlayerEntity();
        wyssen.setNumberPlayer(8);
        wyssen.setFirstNamePlayer("Romain");
        wyssen.setLastNamePlayer("Wyssen");
        wyssen.setBirthdatePlayer(1993);
        wyssen.setPicturePlayer("wyssen.jpg");
        wyssen.setPositionPlayer("Winger");
        wyssen.setLicensePlayer("CH");
        wyssen.setPointsPlayer(1);
        wyssen.setFK_idClub(6);
        wyssen.setSystemPlayer(true);

        // 30 - Arthur Devouassoux #10
        PlayerEntity devouassoux = new PlayerEntity();
        devouassoux.setNumberPlayer(10);
        devouassoux.setFirstNamePlayer("Arthur");
        devouassoux.setLastNamePlayer("Devouassoux");
        devouassoux.setBirthdatePlayer(1998);
        devouassoux.setPicturePlayer("devouassoux.jpg");
        devouassoux.setPositionPlayer("Winger");
        devouassoux.setLicensePlayer("CH");
        devouassoux.setPointsPlayer(3);
        devouassoux.setFK_idClub(6);
        devouassoux.setSystemPlayer(true);

        players.add(giovannini);
        players.add(massy);
        players.add(meyrat);
        players.add(wyssen);
        players.add(devouassoux);


        // HC Moutier (7)
        // 31 - Thierry Wermeille #31
        PlayerEntity wermeille = new PlayerEntity();
        wermeille.setNumberPlayer(31);
        wermeille.setFirstNamePlayer("Thierry");
        wermeille.setLastNamePlayer("Wermeille");
        wermeille.setBirthdatePlayer(1986);
        wermeille.setPicturePlayer("wermeille.jpg");
        wermeille.setPositionPlayer("Goalie");
        wermeille.setLicensePlayer("CH");
        wermeille.setPointsPlayer(0);
        wermeille.setFK_idClub(7);
        wermeille.setSystemPlayer(true);

        // 32 - Nelson Boldini #6
        PlayerEntity boldini = new PlayerEntity();
        boldini.setNumberPlayer(6);
        boldini.setFirstNamePlayer("Nelson");
        boldini.setLastNamePlayer("Boldini");
        boldini.setBirthdatePlayer(1996);
        boldini.setPicturePlayer("boldini.jpg");
        boldini.setPositionPlayer("Defense");
        boldini.setLicensePlayer("CH");
        boldini.setPointsPlayer(0);
        boldini.setFK_idClub(7);
        boldini.setSystemPlayer(true);

        // 33 - Maël Seuret #17
        PlayerEntity seuret = new PlayerEntity();
        seuret.setNumberPlayer(17);
        seuret.setFirstNamePlayer("Maël");
        seuret.setLastNamePlayer("Seuret");
        seuret.setBirthdatePlayer(1999);
        seuret.setPicturePlayer("seuret.jpg");
        seuret.setPositionPlayer("Defense");
        seuret.setLicensePlayer("CH");
        seuret.setPointsPlayer(1);
        seuret.setFK_idClub(7);
        seuret.setSystemPlayer(true);

        // 34 - Corentin Berthoud #4
        PlayerEntity berthoud = new PlayerEntity();
        berthoud.setNumberPlayer(4);
        berthoud.setFirstNamePlayer("Corentin");
        berthoud.setLastNamePlayer("Berthoud");
        berthoud.setBirthdatePlayer(1989);
        berthoud.setPicturePlayer("berthoud.jpg");
        berthoud.setPositionPlayer("Winger");
        berthoud.setLicensePlayer("CH");
        berthoud.setPointsPlayer(1);
        berthoud.setFK_idClub(7);
        berthoud.setSystemPlayer(true);

        // 35 - Karim Charpié #26
        PlayerEntity charpie = new PlayerEntity();
        charpie.setNumberPlayer(26);
        charpie.setFirstNamePlayer("Karim");
        charpie.setLastNamePlayer("Charpié");
        charpie.setBirthdatePlayer(2000);
        charpie.setPicturePlayer("charpie.jpg");
        charpie.setPositionPlayer("Winger");
        charpie.setLicensePlayer("CH");
        charpie.setPointsPlayer(1);
        charpie.setFK_idClub(7);
        charpie.setSystemPlayer(true);

        players.add(wermeille);
        players.add(boldini);
        players.add(seuret);
        players.add(berthoud);
        players.add(charpie);


        // HC Prilly (8)
        // 36 - Romain Martin #96
        PlayerEntity martin = new PlayerEntity();
        martin.setNumberPlayer(96);
        martin.setFirstNamePlayer("Romain");
        martin.setLastNamePlayer("Martin");
        martin.setBirthdatePlayer(1996);
        martin.setPicturePlayer("");
        martin.setPositionPlayer("Goalie");
        martin.setLicensePlayer("CH");
        martin.setPointsPlayer(0);
        martin.setFK_idClub(8);
        martin.setSystemPlayer(true);

        // 37 - Nicolas Villa #4
        PlayerEntity villa = new PlayerEntity();
        villa.setNumberPlayer(4);
        villa.setFirstNamePlayer("Nicolas");
        villa.setLastNamePlayer("Villa");
        villa.setBirthdatePlayer(1989);
        villa.setPicturePlayer("");
        villa.setPositionPlayer("Defense");
        villa.setLicensePlayer("CH");
        villa.setPointsPlayer(1);
        villa.setFK_idClub(8);
        villa.setSystemPlayer(true);

        // 38 - Kilian Chetelat #88
        PlayerEntity chetelat = new PlayerEntity();
        chetelat.setNumberPlayer(88);
        chetelat.setFirstNamePlayer("Kilian");
        chetelat.setLastNamePlayer("Chetelat");
        chetelat.setBirthdatePlayer(1993);
        chetelat.setPicturePlayer("");
        chetelat.setPositionPlayer("Defense");
        chetelat.setLicensePlayer("CH");
        chetelat.setPointsPlayer(4);
        chetelat.setFK_idClub(8);
        chetelat.setSystemPlayer(true);

        // 39 - Dylan Cordey #1
        PlayerEntity cordey = new PlayerEntity();
        cordey.setNumberPlayer(1);
        cordey.setFirstNamePlayer("Dylan");
        cordey.setLastNamePlayer("Cordey");
        cordey.setBirthdatePlayer(1992);
        cordey.setPicturePlayer("");
        cordey.setPositionPlayer("Winger");
        cordey.setLicensePlayer("CH");
        cordey.setPointsPlayer(1);
        cordey.setFK_idClub(8);
        cordey.setSystemPlayer(true);

        // 40 - Kévin Demierre #10
        PlayerEntity demierre = new PlayerEntity();
        demierre.setNumberPlayer(10);
        demierre.setFirstNamePlayer("Kévin");
        demierre.setLastNamePlayer("Demierre");
        demierre.setBirthdatePlayer(1993);
        demierre.setPicturePlayer("");
        demierre.setPositionPlayer("Winger");
        demierre.setLicensePlayer("CH");
        demierre.setPointsPlayer(2);
        demierre.setFK_idClub(8);
        demierre.setSystemPlayer(true);

        players.add(martin);
        players.add(villa);
        players.add(chetelat);
        players.add(cordey);
        players.add(demierre);


        return players;
    }
}

