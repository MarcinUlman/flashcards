package dev.ulman.flashcards.api;

import dev.ulman.flashcards.model.Card;
import dev.ulman.flashcards.model.Group;
import dev.ulman.flashcards.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping ("groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public String getAllGroups(Model model){
        List<Group> groups = new ArrayList<>();
        groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "groups";
    }

    @GetMapping("{id}")
    public String getGroupById(@PathVariable("id") int id, Model model){
        Group group = groupService.getGroupById(id);
        if (group == null) return "redirect:errors/error-404";
        List<Card> cards = new ArrayList<>();
        cards = groupService.getAllCards(group);
        model.addAttribute("group", group);
        model.addAttribute("cards", cards);
        return "group";
    }

}
