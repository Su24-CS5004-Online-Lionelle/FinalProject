package group8.model.helpers;

import java.util.Comparator;
import group8.model.Enums.Field;
import group8.model.TriviaQuestion;

public final class Sorter {
    /**
     * Private constructor to prevent instantiation.
     */
    private Sorter() {
    }

    /**
     * Returns a comparator for sorting TriviaQuestion objects based on the specified
     * field in ascending order.
     *
     * @param  field The field to sort by.
     * @return A comparator for sorting TriviaQuestion objects by the specified field in
     *         ascending order.
     */
    public static Comparator<TriviaQuestion> getSort(Field field) {
        return getSort(field, true);
    }

    /**
     * Returns a comparator for sorting TriviaQuestion objects based on the specified
     * field and order.
     *
     * @param field     The field to sort by.
     * @param ascending If true, the comparator sorts in ascending order; if false,
     *                  in descending order.
     * @return A comparator for sorting TriviaQuestion objects by the specified field in
     *         the specified order.
     */
    public static Comparator<TriviaQuestion> getSort(Field field, boolean ascending) {
        Comparator<TriviaQuestion> comparator;

        switch (field) {
            case CATEGORY:
                comparator = Comparator.comparing(TriviaQuestion::category);
                break;
            case DIFFICULTY:
                comparator = Comparator.comparing(TriviaQuestion::difficulty);
                break;
            case TYPE:
                comparator = Comparator.comparing(TriviaQuestion::type);
                break;
            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }

        return ascending ? comparator : comparator.reversed();
    }
}