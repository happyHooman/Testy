package com.sdl.selenium.web.table;

import com.sdl.selenium.web.SearchType;
import com.sdl.selenium.web.WebLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Row extends AbstractRow {
    private static final Logger LOGGER = LoggerFactory.getLogger(Row.class);

    public Row() {
        withRenderMillis(200);
        withClassName("Row");
        withTag("tr");
        getPathBuilder().defaultSearchTextType.add(SearchType.DEEP_CHILD_NODE_OR_SELF);
    }

    public Row(WebLocator container) {
        this();
        withContainer(container);
    }

    public Row(WebLocator container, int indexRow) {
        this(container);
        withPosition(indexRow);
    }

    public Row(WebLocator table, String searchElement, SearchType... searchTypes) {
        this(table);
        withText(searchElement, searchTypes);
    }

    public Row(WebLocator table, AbstractCell... cells) {
        this(table);
        withChildNodes(cells);
    }

    public Row(WebLocator table, int indexRow, AbstractCell... cells) {
        this(table, indexRow);
        withChildNodes(cells);
    }

    public List<String> getCellsText() {
        WebLocator columnsEl = new WebLocator(this).withTag("td");
        int columns = columnsEl.size() + 1;

        List<String> list = new ArrayList<String>();
        for (int j = 1; j < columns; j++) {
            Cell cell = new Cell(this, j);
            list.add(cell.getText());
        }
        return list;
    }
}
