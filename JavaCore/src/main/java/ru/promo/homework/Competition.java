package ru.promo.homework;

import ru.promo.homework.Anitonchik.AnitonchikMap;
import ru.promo.homework.BibaevRuslan.BibaevRuslanMap;
import ru.promo.homework.Dok3R73.Dok3R73Map;
import ru.promo.homework.NikitaMorozka.NikitaMorozkaMap;
import ru.promo.homework.SalihkYaruskin.SalikhYaruskinMap;
import ru.promo.homework.danilKraun.DanilKraunMap;
import ru.promo.homework.egorLight.EgorMap;
import ru.promo.homework.frostyklol.FrostyklolMap;
import ru.promo.homework.funa4i.Funa4iMap;
import ru.promo.homework.klineMolodoy.KlineMolodoyMap;
import ru.promo.homework.shamanStepapDed.ShamanStepanDedMap;
import ru.promo.homework.sofiaivv.SofiaivvMap;
import ru.promo.homework.vladislove073.Vladislove073Map;
import ru.promo.homework.ya5uhiro.Ya5uhiroMap;
import ru.promo.homework.ziraelc.ZiraelcMap;

import java.util.List;
import java.util.Map;

public class Competition {
    private final static List<Map<String, Task>> impls = List.of(
            new AnitonchikMap<>(),
            new BibaevRuslanMap<>(),
            new DanilKraunMap<>(),
            new Dok3R73Map<>(),
            new EgorMap<>(),
            new FrostyklolMap<>(),
            new Funa4iMap<>(),
            new KlineMolodoyMap<>(),
            new NikitaMorozkaMap<>(16),
            new SalikhYaruskinMap<>(),
            new ShamanStepanDedMap<>(),
            new SofiaivvMap<>(16),
            new Vladislove073Map<>(),
            new Ya5uhiroMap<>(),
            new ZiraelcMap<>(16)
    );

    public static void main(String[] args) {

    }
}
