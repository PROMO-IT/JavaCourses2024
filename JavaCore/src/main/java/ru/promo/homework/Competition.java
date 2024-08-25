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

import java.util.*;

public class Competition {
    private final static List<Map<UUID, Task>> impls2 = List.of(
            new BibaevRuslanMap<>(),
            new DanilKraunMap<>(),
//            new FrostyklolMap<>(),
            new ShamanStepanDedMap<>(),
            new SofiaivvMap<>(16)
    );

    private final static List<Map<UUID, Task>> impls = List.of(
            new AnitonchikMap<>(),
            new Dok3R73Map<>(),
            new KlineMolodoyMap<>(),
            new EgorMap<>(),
//            new Funa4iMap<>(),
            new NikitaMorozkaMap<>(16),
            new SalikhYaruskinMap<>(),
            new Vladislove073Map<>(),
            new Ya5uhiroMap<>(),
            new ZiraelcMap<>(16)
    );

    public static void main(String[] args) {
        List<Task> tasks = initTasks(100000);
        impls.forEach(impl ->
                tasks.forEach(task ->
                        impl.put(task.getId(), task)
                )
        );

        impls.stream()
                .map(impl -> impl.getClass().getName() + " : " +  impl.size())
                .forEach(System.out::println);

        Collections.shuffle(tasks);
        System.out.println("Result");


        impls.stream()
                .map(impl -> {
                    long l = System.currentTimeMillis();
                    tasks.forEach(task -> {
                        Task foundTask = impl.get(task.getId());
//                if (!foundTask.getId().equals(task.getId())) {
//                    System.out.println(impl.getClass().getName() + " works wrong!");
//                }
                    });

                    long time = System.currentTimeMillis() - l;
                    return impl.getClass().getName() + " : " + time;
                })
                .forEach(System.out::println);

    }

    private static List<Task> initTasks(int count) {
        List<Task> tasks = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            Task task = new Task(UUID.randomUUID(), "Task" + i);
            tasks.add(task);
        }
        return tasks;
    }
}
