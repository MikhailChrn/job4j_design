package ru.job4j.question;

import java.util.*;

public class Analize {
    private static Iterator<User> iteratorPrevious;
    private static Iterator<User> iteratorCurrent;
    private static User userCursor;

    private static int analizeAdded(Set<User> previous, Set<User> current) {
        iteratorCurrent = current.iterator();
        int added = 0;
        while (iteratorCurrent.hasNext()) {
            userCursor = iteratorCurrent.next();
            if (!previous.contains(userCursor)) {
                added++;
            }
        }
        return added;
    }

    private static int analizeDeleted(Set<User> previous, Set<User> current) {
        iteratorPrevious = previous.iterator();
        int deleted = 0;
        while (iteratorPrevious.hasNext()) {
            userCursor = iteratorPrevious.next();
            if (!current.contains(userCursor)) {
                deleted++;
            }
        }
        return deleted;
    }

    private static int analizeChanged(Set<User> previous, Set<User> current) {
        iteratorPrevious = previous.iterator();
        HashMap<Integer, String> previousMap = new HashMap<>();
        while (iteratorPrevious.hasNext()) {
            userCursor = iteratorPrevious.next();
            previousMap.put(userCursor.getId(), userCursor.getName());
        }
        int changed = 0;
        iteratorCurrent = current.iterator();
        while (iteratorCurrent.hasNext()) {
            userCursor = iteratorCurrent.next();
            if (previousMap.containsKey(userCursor.getId())
                    && !userCursor.getName()
                    .equals(previousMap.get(userCursor.getId()))) {
                changed++;
            }
        }
        return changed;
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        return new Info(analizeAdded(previous, current),
                         analizeChanged(previous, current),
                            analizeDeleted(previous, current));
    }

}
