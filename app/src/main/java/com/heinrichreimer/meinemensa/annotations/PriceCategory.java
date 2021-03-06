/*
 * MIT License
 *
 * Copyright (c) 2017 Jan Heinrich Reimer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.heinrichreimer.meinemensa.annotations;

import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.util.SparseIntArray;

import com.heinrichreimer.meinemensa.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.heinrichreimer.meinemensa.annotations.PriceCategory.EMPLOYEES;
import static com.heinrichreimer.meinemensa.annotations.PriceCategory.GUESTS;
import static com.heinrichreimer.meinemensa.annotations.PriceCategory.STUDENTS;

@IntDef({STUDENTS, EMPLOYEES, GUESTS})
@Retention(RetentionPolicy.SOURCE)
public @interface PriceCategory {
    int STUDENTS = 1;
    int EMPLOYEES = 2;
    int GUESTS = 3;
    int[] ALL = {STUDENTS, EMPLOYEES, GUESTS};

    class Converter {
        private static final SparseIntArray ID_TO_PRICE_CATEGORY = new SparseIntArray(3);

        static {
            ID_TO_PRICE_CATEGORY.put(R.id.menu_item_price_students, STUDENTS);
            ID_TO_PRICE_CATEGORY.put(R.id.menu_item_price_employees, EMPLOYEES);
            ID_TO_PRICE_CATEGORY.put(R.id.menu_item_price_guests, GUESTS);
        }

        private static final SparseIntArray PRICE_CATEGORY_TO_ID = new SparseIntArray(3);

        static {
            PRICE_CATEGORY_TO_ID.put(STUDENTS, R.id.menu_item_price_students);
            PRICE_CATEGORY_TO_ID.put(EMPLOYEES, R.id.menu_item_price_employees);
            PRICE_CATEGORY_TO_ID.put(GUESTS, R.id.menu_item_price_guests);
        }

        @SuppressWarnings("WrongConstant")
        @PriceCategory
        public static int toPriceCategory(@IdRes int id) {
            return ID_TO_PRICE_CATEGORY.get(id, STUDENTS);
        }

        @IdRes
        public static int toId(@PriceCategory int category) {
            return PRICE_CATEGORY_TO_ID.get(category, R.id.menu_item_price_students);
        }

        public static boolean isPriceCategory(int possibleCategory) {
            return PRICE_CATEGORY_TO_ID.indexOfKey(possibleCategory) >= 0;
        }

        public static boolean isId(@IdRes int possibleId) {
            return ID_TO_PRICE_CATEGORY.indexOfKey(possibleId) >= 0;
        }
    }
}