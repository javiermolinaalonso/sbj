package com.socialblackjack.game.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.socialblackjack.game.BlackjackGame;
import com.socialblackjack.game.GameOptionsEnumeration;
import com.socialblackjack.game.annotations.BlackjackAction;
import com.socialblackjack.game.annotations.CurrentHand;
import com.socialblackjack.game.annotations.CurrentPlayer;
import com.socialblackjack.entities.Player;
import com.socialblackjack.game.exceptions.IllegalActionException;
import com.socialblackjack.game.impl.BlackjackGameImpl;
import com.socialblackjack.hand.BlackjackHand;

@Aspect
@Component
public class ActionAspect {
	
	@Around("execution(@com.socialblackjack.game.annotations.BlackjackAction * *(..))")
	public Object validate(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		int i = 0;
		BlackjackHand hand = null;
		Player player = null;
		for (Annotation[] annotations : method.getParameterAnnotations()) {
			for(Annotation annotation : annotations){
				if(annotation.annotationType().getName().equals(CurrentHand.class.getCanonicalName())){
					hand = (BlackjackHand) joinPoint.getArgs()[i];
				}else if(annotation.annotationType().getName().equals(CurrentPlayer.class.getCanonicalName())){
					player = (Player) joinPoint.getArgs()[i];
				}
			}
			i++;
		}
		
		if(hand != null){
			if(hand.getHigherValue() >= BlackjackHand.MAX_VALUE)
				throw new IllegalActionException();
		}
		BlackjackAction bjAction = method.getAnnotation(BlackjackAction.class);
		BlackjackGame game = ((BlackjackGameImpl)joinPoint.getTarget());
		Object result = null;
		if(bjAction.action().equals(GameOptionsEnumeration.INSURANCE)){
			System.out.println("The player "+player.getFirstName()+ " has made "+bjAction.action().toString());
			if(game.getGameOptions(player).contains(GameOptionsEnumeration.INSURANCE)){
				joinPoint.proceed();
			}else{
				System.out.println("The player "+player.getFirstName()+ " has made "+bjAction.action().toString()+" but it is not available");
			}
		}else{
			if(game.isActionAvailable(hand, bjAction.action())){
				System.out.println("The player "+player.getFirstName()+ " has made "+bjAction.action().toString()+" with the hand "+hand.toString());
				result = joinPoint.proceed();
				if(bjAction.action().equals(GameOptionsEnumeration.SPLIT)){
					List<BlackjackHand> hands = (List<BlackjackHand>)result;
					for(BlackjackHand bjhand : hands){
						List<GameOptionsEnumeration> options = game.updateHandOptions(player, bjhand);
						System.out.println("The player "+player.getFirstName()+ " has made "+bjAction.action().toString()+" and the result was "+bjhand.toString()+". New options are "+options.toString());
						game.updateDoneActions(bjhand, bjAction.action());
					}
				}else{
					List<GameOptionsEnumeration> options = game.updateHandOptions(player, hand);
					System.out.println("The player "+player.getFirstName()+ " has made "+bjAction.action().toString()+" and the result was "+hand.toString()+". New options are "+options.toString());
					game.updateDoneActions(hand, bjAction.action());
				}
			}else{
				System.out.println("The player "+player.getFirstName()+ " has made "+bjAction.action().toString()+" with the hand "+hand.toString() + " but it is not available");
			}
		}
		System.out.println();
		
		return result;
	}
}