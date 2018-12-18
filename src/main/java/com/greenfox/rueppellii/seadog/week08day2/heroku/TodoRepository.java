package com.greenfox.rueppellii.seadog.week08day2.heroku;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}