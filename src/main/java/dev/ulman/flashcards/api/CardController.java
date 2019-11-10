package dev.ulman.flashcards.api;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.services.CardService;
import dev.ulman.flashcards.services.GroupService;
import dev.ulman.flashcards.validators.CardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("cards")
public class CardController {

    private CardService cardService;
    private CardValidator validator;
    private GroupService groupService;

    @Autowired
    public CardController(CardService cardService, CardValidator validator, GroupService groupService) {
        this.cardService = cardService;
        this.validator = validator;
        this.groupService = groupService;
    }

    @GetMapping("{id}")
    public String getCard (@PathVariable("id") int id, Model model){
        Card card = cardService.getCardById(id);
        if (card == null){
            return "redirect:errors/error-404";
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
    public String addCard(@ModelAttribute("card") Card card, Model model, BindingResult result){
        validator.validate(card, result);

        if (result.hasErrors()){
            model.addAttribute("groups", groupService.getAllGroups());
            return "cardForm";
        }
        cardService.addCard(card);
        model.addAttribute("card", card);
        model.addAttribute("message", "message.successfully_added");
        return "card";
    }

    @DeleteMapping("{id}")
    public String deleteCard(@PathVariable("id") int id, Model model){
        Card card = cardService.getCardById(id);
        if (card == null){
            return "redirect:errors/error-404";
        }

        cardService.deleteCard(id);
        model.addAttribute("message", "message.successfully_deleted");
        return "card";
    }

    @GetMapping("editCard")
    public String displayCardFormToEdit(@RequestParam(value = "CardId", required = true) int id, Model model){
        Card card = cardService.getCardById(id);
        if (card == null){
            return "redirect:errors/error-404";
        }
        model.addAttribute(card);
        model.addAttribute("groups", groupService.getAllGroups());
        return "cardForm";
    }
    @PostMapping("editCard")
    public String editCard(@ModelAttribute Card card, Model model, BindingResult result){
        validator.validate(card, result);
        if (result.hasErrors()){
            return "cardForm";
        }

        cardService.updateCard(card.getId(), card);

        model.addAttribute("card", card);
        model.addAttribute("message", "message.successfully_changed");
        return "card";
    }


}
