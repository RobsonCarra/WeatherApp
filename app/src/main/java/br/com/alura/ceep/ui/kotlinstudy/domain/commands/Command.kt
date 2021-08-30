package br.com.alura.ceep.ui.kotlinstudy.domain.commands

public interface Command<out T> {
    fun execute(): T
}