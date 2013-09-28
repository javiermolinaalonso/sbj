package com.socialblackjack.game.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.socialblackjack.game.GameOptionsEnumeration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BlackjackAction {

	GameOptionsEnumeration action();

}
