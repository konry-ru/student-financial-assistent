package ru.konry.stud_fin_assistent.domains.registry;

import java.util.ArrayList;
import java.util.List;

public class AnswerCityRegistry {
    private List<AnswerCityRegistryItem> items;

    public void addItem(AnswerCityRegistryItem item) {
        if(items == null) {
            items = new ArrayList<>(10);
        }
        items.add(item);
    }

    public List<AnswerCityRegistryItem> getItems() {
        return items;
    }
}
