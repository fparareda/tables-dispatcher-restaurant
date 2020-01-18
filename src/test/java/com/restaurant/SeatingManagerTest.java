package com.restaurant;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SeatingManagerTest {

    SeatingManager seatingManager;

    @Before
    public void setUp() throws Exception {
        seatingManager = new SeatingManager(Arrays.asList(
                                                new Table(2),
                                                new Table(3),
                                                new Table(4),
                                                new Table(5),
                                                new Table(6)),
                                            new ArrayList<>());
    }

    @Test(expected = AssertionError.class)
    public void given_null_group_return_exception_on_arrives() {
        seatingManager.arrives(null);
    }

    @Test(expected = AssertionError.class)
    public void given_null_group_return_exception_on_locate() {
        seatingManager.locate(null);
    }

    @Test(expected = AssertionError.class)
    public void given_null_group_return_exception_on_leaves() {
        seatingManager.leaves(null);
    }

    @Test
    public void given_different_tables_the_group_of_six_are_seated() {
        CustomerGroup customerGroup = new CustomerGroup(6, "Mark Robbins");
        seatingManager.arrives(customerGroup);
        assertNotNull(seatingManager.locate(customerGroup));
    }

    @Test
    public void given_different_tables_two_groups_of_six_wants_to_be_seated_but_only_one_can() {
        CustomerGroup customerGroup1 = new CustomerGroup(6, "Mark Robbins");
        CustomerGroup customerGroup2 = new CustomerGroup(6, "John Doe");

        Table tableSize5 = new Table(5);
        Table tableSize6 = new Table(6);
        SeatingManager seatingManager = new SeatingManager(Arrays.asList(
                                                            tableSize5,
                                                            tableSize6),
                                                            new ArrayList<>());
        seatingManager.arrives(customerGroup1);
        seatingManager.arrives(customerGroup2);
        assertEquals(seatingManager.locate(customerGroup1), tableSize6);
        assertNull(seatingManager.locate(customerGroup1));
    }

    @Test
    public void given_different_tables_four_groups_of_two_want_to_be_seated_and_put_it_in_different_tables_size() {
        CustomerGroup customerGroup1 = new CustomerGroup(2, "Mark Robbins");
        CustomerGroup customerGroup2 = new CustomerGroup(2, "John Doe");
        CustomerGroup customerGroup3 = new CustomerGroup(2, "Liuba Markhoff");
        CustomerGroup customerGroup4 = new CustomerGroup(2, "Gonzalo Gonzalves");

        Table tableSize2 = new Table(2);
        Table tableSize3 = new Table(3);
        Table tableSize4 = new Table(4);
        Table tableSize5 = new Table(5);
        Table tableSize6 = new Table(6);
        SeatingManager seatingManager = new SeatingManager(Arrays.asList(
                                            tableSize2,
                                            tableSize3,
                                            tableSize4,
                                            tableSize5,
                                            tableSize6),
                                            new ArrayList<>());

        seatingManager.arrives(customerGroup1);
        seatingManager.arrives(customerGroup2);
        seatingManager.arrives(customerGroup3);
        seatingManager.arrives(customerGroup4);

        assertEquals(seatingManager.locate(customerGroup1), tableSize2);
        assertEquals(seatingManager.locate(customerGroup2), tableSize3);
        assertEquals(seatingManager.locate(customerGroup3), tableSize4);
        assertEquals(seatingManager.locate(customerGroup4), tableSize4);
    }

    @Test
    public void given_two_tables_three_groups_of_five_want_to_be_seated_but_only_two_can() {
        CustomerGroup customerGroup1 = new CustomerGroup(2, "Mark Robbins");
        CustomerGroup customerGroup2 = new CustomerGroup(2, "John Doe");
        CustomerGroup customerGroup3 = new CustomerGroup(2, "Liuba Markhoff");

        Table tableSize2 = new Table(2);
        Table tableSize3 = new Table(3);
        SeatingManager seatingManager = new SeatingManager(Arrays.asList(
                tableSize2,
                tableSize3),
                new ArrayList<>());

        seatingManager.arrives(customerGroup1);
        seatingManager.arrives(customerGroup2);
        seatingManager.arrives(customerGroup3);

        assertEquals(seatingManager.locate(customerGroup1), tableSize2);
        assertEquals(seatingManager.locate(customerGroup2), tableSize3);
        assertNull(seatingManager.locate(customerGroup3));
    }
}
