package ru.job4j.generics.store;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsSalesman() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Salesman"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Salesman"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.add(new Role("2", "Salesman"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

    @Test
    public void whenReplaceThenRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Salesman"));
        store.replace("1", new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.replace("10", new Role("10", "Salesman"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsSalesman() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

}