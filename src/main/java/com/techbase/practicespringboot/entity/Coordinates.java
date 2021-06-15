package com.techbase.practicespringboot.entity;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Coordinates {
    Vietnam("105.89432106936329, 21.03253715957912", "Hà Nội, Vietnam"),
    Japan("139.69795260761362, 35.68854237816038", "Tokyo, Japan"),
    SouthKorea("127.06971747700345, 37.57442286009356", "Seoul, SouthKorea"),
    Russia("38.066082549936596, 55.790967002550644", "Moscow, Russia"),
    UnitedStates("-119.73960140117549, 48.06088672155687", "Washington D.C, UnitedStates"),
    Canada("-75.65827897560547, 45.425411162762", "Ottawa, Canada"),
    Australia("149.13050024776146, -35.29782227698426", "Canberra, Australia"),
    England("-0.06889954939492782, 51.50841313671032", "London, England"),
    France("2.356190395883118, 48.8568839551796", "Paris, France"),
    Germany("13.432386179335708, 52.51638352285843", "Berlin, Germany"),
    Italy("12.54673260891036, 41.89966537704623", "Rome, Italy"),
    Portugal("-8.544914735159864, 38.81131645620778", "Lisbon,Portugal"),
    Spain("-3.618157085306734, 40.42052307484241", "Madrid, Spain"),
    Poland("21.058032916860157, 52.237696120678635", "Warsaw, Poland"),
    Mexico("-99.64564497407684, 19.650166219556212", "México, Mexico"),
    Cuba("-82.30319895500992, 23.14234125869983", "Havana, Cuba"),
    Brazil("-47.83229618858854, -15.790703161911026", "Brasilia, Brazil"),
    Argentina("-58.370650278427206, -34.60477508106535", "Buenos Aires, Argentina"),
    SaudiArabia("46.77354190492281, 24.636189273725904", "Riyadh, Saudi Arabia"),
    India("77.26997769120248, 28.61821283864505", "New Delhi, India"),
    Singapore("103.81748619339646, 1.3559049581189555", "Singapore, Singapore");


    public final String position;
    public final String names;

    public static Coordinates fromText(String value) {
        return Arrays.stream(values())
                .filter(bl -> bl.names.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
