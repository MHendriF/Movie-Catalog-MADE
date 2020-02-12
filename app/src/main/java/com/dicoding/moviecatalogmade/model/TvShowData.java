package com.dicoding.moviecatalogmade.model;

import java.util.ArrayList;

public class TvShowData {
    private static String[] Title = {
            "Naruto Shippūden",
            "Dragon Ball",
            "Fairy Tail",

            "Marvel's Iron Fist",
            "Arrow",
            "Doom Patrol",
            "Family Guy",
            "The Flash",
            "Gotham",
            "Grey's Anatomy",
            "Hanna"
    };

    private static String[] Runtime = {
            "22m",
            "25m",
            "25m",

            "55m",
            "42m",
            "60m",
            "22m",
            "44m",
            "43m",
            "43m",
            "50m"


    };

    private static String[] Overview = {
            "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
            "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the dragon balls brought her to Goku's home. Together, they set off to find all seven dragon balls in an adventure that would change Goku's life forever. See how Goku met his life long friends Bulma, Yamcha, Krillin, Master Roshi and more. And see his adventures as a boy, all leading up to Dragonball Z and later Dragonball GT.",
            "Lucy is a 17-year-old girl, who wants to be a full-fledged mage. One day when visiting Harujion Town, she meets Natsu, a young man who gets sick easily by any type of transportation. But Natsu isn't just any ordinary kid, he's a member of one of the world's most infamous mage guilds: Fairy Tail.",

            "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.  ",
            "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash. ",
            "Before there was Batman, there was GOTHAM. Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film."

    };

    private static int[] Score = {
            75,
            71,
            64,

            62,
            61,
            58,
            65,
            67,
            69,
            63,
            64

    };

    private static String[] Release_date = {
            "February 15, 2007",
            "February 26, 1986",
            "October 12, 2009",

            "March 17, 2017",
            "October 10, 2012",
            "February 15, 2019",
            "January 31, 1999",
            "October 7, 2014",
            "September 22, 2014",
            "March 27, 2005",
            "March 28, 2019"

    };

    private static String[] Language = {
            "Japanese",
            "Japanese",
            "Japanese",

            "English",
            "English",
            "English",
            "English",
            "English",
            "English",
            "English",
            "English"
    };


    private static String[] Poster = {
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/zAYRe2bJxpWTVrwwmBc00VFkAf4.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/3wx3EAMtqnbSLhGG8NrqXriCUIQ.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/58GKcwFV3lpVOGzybeMrrNOjHpz.jpg",

            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/nv4nLXbDhcISPP8C1mgaxKU50KO.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/nVN7Dt0Xr78gnJepRsRLaLYklbY.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/fki3kBlwJzFp8QohL43g9ReV455.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/4XddcRDtnNjYmLRMYpbrhFxsbuq.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/eqgIOObafPJitt8JNh1LuO2fvqu.jpg",
            "https://image.tmdb.org/t/p/w185_and_h278_bestv2/5nSSkcM3TgpllZ7yTyBOQEgAX36.jpg"

    };

    public static ArrayList<TvShow> getListData() {
        ArrayList<TvShow> list = new ArrayList<>();
        for (int position = 0; position < Title.length; position++){
            TvShow tvShow = new TvShow();
            tvShow.setTitle(Title[position]);
            tvShow.setRelease_date(Release_date[position]);
            tvShow.setOverview(Overview[position]);
            tvShow.setScore(Score[position]);
            tvShow.setPoster(Poster[position]);
            tvShow.setRuntime(Runtime[position]);
        }
        return list;
    }
}