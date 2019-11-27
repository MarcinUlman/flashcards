package dev.ulman.flashcards.api;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.services.CardService;
import dev.ulman.flashcards.services.GroupService;
import dev.ulman.flashcards.validators.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("cards")
public class CardController {

    private CardService cardService;
    private CardValidator validator;
    private GroupService groupService;
    private MessageSource messageSource;

    @Autowired
    public CardController(CardService cardService, CardValidator validator, GroupService groupService, MessageSource messageSource) {
        this.cardService = cardService;
        this.validator = validator;
        this.groupService = groupService;
        this.messageSource = messageSource;
    }

    @GetMapping("{id}")
    public String getCard (@PathVariable("id") int id, Model model){
        Card card = cardService.getCardById(id);
        if (card == null){
            return "errors/error-404";
        }
        model.addAttribute("card", card);
        return "card";
    }

    @GetMapping("addCard")
    public String displayCardForm(Model model){
        model.addAttribute("card", new Card());
        model.addAttribute("groups", groupService.getAllGroups());
        return "cardForm";
    }

    @PostMapping("addCard")
    public String addCard(@ModelAttribute("card") Card card, BindingResult result, Model model, Locale locale){
        validator.validate(card, result);

        if (result.hasErrors()){
            model.addAttribute("groups", groupService.getAllGroups());
            return "cardForm";
        }


        if (cardService.addCard(card)) {
            model.addAttribute("card", card);
            String message = messageSource.getMessage("message.successfully_added", null, "Message", locale);
            model.addAttribute("message", message);
            model.addAttribute("messageColor", "ok");
            return "card";
        }
        return "errors/error-507";
    }

    @GetMapping("delete/{id}")
    public String deleteCard(@PathVariable("id") int id, Model model, Locale locale){
        Card card = cardService.getCardById(id);
        if (card == null){
            return "redirect:errors/error-404";
        }

        if (cardService.deleteCard(id)) {
            String message = messageSource.getMessage("message.successfully_deleted", null, "Message", locale);
            model.addAttribute("message", message);
            model.addAttribute("messageColor", "alert");
            return "index";
        }
        return "errors/error-507";
    }

    @GetMapping("edit/{id}")
    public String displayCardFormToEdit(@PathVariable("id") int id, Model model){
        Card card = cardService.getCardById(id);
        if (card == null){
            return "errors/error-404";
        }
        model.addAttribute(card);
        model.addAttribute("groups", groupService.getAllGroups());
        return "cardForm";
    }

    @PostMapping("edit")
    public String editCard(@ModelAttribute Card card, BindingResult result, Model model, Locale locale){
        validator.validate(card, result);
        if (result.hasErrors()){
            model.addAttribute("groups", groupService.getAllGroups());
            return "cardForm";
        }

        if (cardService.updateCard(card.getId(), card)) {
            model.addAttribute("card", card);
            String message = messageSource.getMessage("message.successfully_changed", null, "Message", locale);
            model.addAttribute("message", message);
            model.addAttribute("messageColor", "ok");
            return "card";
        }
        return "errors/error-507";
    }
}
