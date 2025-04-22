package com.example.fishermans_guide.data;

import android.util.Log;

import com.example.fishermans_guide.model.Fish;
import com.example.fishermans_guide.R;
import java.util.ArrayList;
import java.util.List;

public class DataInitializer {
    public static List<Fish> getPrepopulateFishList() {
        List<Fish> list = new ArrayList<>();

        Fish trout = new Fish();
        trout.name = "Форель";
        trout.description = "Прекрасная пресноводная рыба с нежным вкусом, живёт в горных реках и быстрых ручьях.";
        trout.habitat = "Горные реки и быстрые ручьи";
        trout.season = "Май–сентябрь";
        trout.bait = "Маленькие воблеры, мушки";
        trout.imageResId = R.drawable.trout;
        trout.isFavorite = false;
        list.add(trout);

        Fish pike = new Fish();
        pike.name = "Щука";
        pike.description = "Сильный хищник с острыми зубами, предпочитает прибрежные заросли и затонувшие коряги.";
        pike.habitat = "Пресноводные озёра и медленные реки";
        pike.season = "Май–октябрь";
        pike.bait = "Блесны, живец";
        pike.imageResId = R.drawable.pike;
        pike.isFavorite = false;
        list.add(pike);

        Fish perch = new Fish();
        perch.name = "Окунь";
        perch.description = "Широко распространённая рыба, активна на мелководье и в зарослях водорослей.";
        perch.habitat = "Мелкие заливы, прибрежные заросли";
        perch.season = "Апрель–октябрь";
        perch.bait = "Микровоблеры, твистеры";
        perch.imageResId = R.drawable.perch;
        perch.isFavorite = false;
        list.add(perch);

        Fish carp = new Fish();
        carp.name = "Карп";
        carp.description = "Крупная донная рыба, предпочитает тихие заводи с теплой водой.";
        carp.habitat = "Тихие заводи, озёра";
        carp.season = "Июнь–сентябрь";
        carp.bait = "Кукуруза, бойлы, тесто";
        carp.imageResId = R.drawable.carp;
        carp.isFavorite = false;
        list.add(carp);

        Fish catfish = new Fish();
        catfish.name = "Сом";
        catfish.description = "Ночные хищники, достигают больших размеров, предпочитают глубокие ямы.";
        catfish.habitat = "Глубокие участки рек и озёр";
        catfish.season = "Июнь–сентябрь";
        catfish.bait = "Живец, печень";
        catfish.imageResId = R.drawable.catfish;
        catfish.isFavorite = false;
        list.add(catfish);

        Fish salmon = new Fish();
        salmon.name = "Лосось";
        salmon.description = "Пресноводно-морская рыба, совершает миграцию, известна своей силой и сопротивлением.";
        salmon.habitat = "Устья рек, реки";
        salmon.season = "Июль–октябрь";
        salmon.bait = "Силиконовые приманки, воблеры";
        salmon.imageResId = R.drawable.salmon;
        salmon.isFavorite = false;
        list.add(salmon);

        Fish zander = new Fish();
        zander.name = "Судак";
        zander.description = "Ночной хищник, предпочитает глубокие участки и русловые бровки.";
        zander.habitat = "Глубокие русловые ямы";
        zander.season = "Май–октябрь";
        zander.bait = "твистеры, джиги";
        zander.imageResId = R.drawable.zander;
        zander.isFavorite = false;
        list.add(zander);

        Fish roach = new Fish();
        roach.name = "Плотва";
        roach.description = "Мелкая стайная рыба, часто используется в коммерческой рыбалке и любительском ловле.";
        roach.habitat = "Тихие речные заливы";
        roach.season = "Май–сентябрь";
        roach.bait = "Мотыль, опарыш";
        roach.imageResId = R.drawable.roach;
        roach.isFavorite = false;
        list.add(roach);

        Fish bream = new Fish();
        bream.name = "Лещ";
        bream.description = "Донная рыба, собирается в большие стаи, предпочитает илистое дно.";
        bream.habitat = "Глубокие тихие заводи";
        bream.season = "Июнь–сентябрь";
        bream.bait = "Зерно, тесто, пареная пшеница";
        bream.imageResId = R.drawable.bream;
        bream.isFavorite = false;
        list.add(bream);

        Fish dace = new Fish();
        dace.name = "Язь";
        dace.description = "Сильная стайная рыба, предпочитает течение реки.";
        dace.habitat = "Речные бровки со средним течением";
        dace.season = "Май–сентябрь";
        dace.bait = "Мотыль, опарыш, кукуруза";
        dace.imageResId = R.drawable.dace;
        dace.isFavorite = false;
        list.add(dace);
        Log.d("DB", "Prepopulate " + list.size() + " fishes");
        return list;
    }
}