package com.bnppf.bookShoppingCart.util;

import com.bnppf.bookShoppingCart.enums.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookCollectionAggregator {
    public static List<List<Book>> getBookCollection(List<Book> bookCollection) {
        Map<Book, Integer> bookCountsByTitle = new HashMap<>();
        List<List<Book>> groupedBooks = new ArrayList<>();
        for (Book book : bookCollection) {
            bookCountsByTitle.put(book, bookCountsByTitle.getOrDefault(book, 0) + 1);
        }
        while (!bookCountsByTitle.isEmpty()) {
            List<Book> group = new ArrayList<>();
            for (Book books1 : new ArrayList<>(bookCountsByTitle.keySet())) {
                group.add(books1);
                decrementOrRemove(books1, bookCountsByTitle);
            }
            groupedBooks.add(group);
        }
        getOptimalGroups(groupedBooks);
        return groupedBooks;
    }

    private static void decrementOrRemove(Book title, Map<Book, Integer> map) {
        map.computeIfPresent(title, (k, v) -> v - 1);
        if (map.get(title) != null && map.get(title) <= 0) {
            map.remove(title);
        }
    }

    //This method will check the group size, If the group size is 5 or 3 change it to 4 and 4 which is optimal
    private static void getOptimalGroups(List<List<Book>> groupedBooks) {
        int indexOfGroupSizeFive = -1;
        int indexOfGroupSizeThree = -1;
        for (int i = 0; i < groupedBooks.size(); i++) {
            int size = groupedBooks.get(i).size();
            if (size == 3 && indexOfGroupSizeThree == -1) {
                indexOfGroupSizeThree = i;
            } else if (size == 5 && indexOfGroupSizeFive == -1) {
                indexOfGroupSizeFive = i;
            }
        }

        if (indexOfGroupSizeFive != -1 && indexOfGroupSizeThree != -1) {
            List<Book> groupSixeFiveList = groupedBooks.get(indexOfGroupSizeFive);
            List<Book> groupSixeThisList = groupedBooks.get(indexOfGroupSizeThree);

            for (Book book : new ArrayList<>(groupSixeFiveList)) {
                boolean alreadyExists = groupSixeThisList.stream()
                        .anyMatch(b -> b.getTitle().equals(book.getTitle()));
                if (!alreadyExists) {
                    groupSixeFiveList.remove(book);
                    groupSixeThisList.add(book);
                    break;
                }
            }
        }
    }
}
