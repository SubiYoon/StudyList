package hello.itemservice.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                        @RequestParam Integer price,
                        @RequestParam Integer quantity,
                         Model model
    ) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);

        model.addAttribute("items", itemRepository.findAll());
        return "basic/items";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item) {
        itemRepository.save(item);
//        만약 @ModelAttribute에 ("item")을 생략하면 자동적으로 Class명의 앞글자가 소문자로 변경된 데이터가 addAttribute에 추가된다.
//        model.addAttribute("item", item); // @ModelAttribute에 ("item")을 작성해주면 자동 추가 되기 때문에 생략 가능
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(Item item) {
        itemRepository.save(item);
        // ModelAttribute는 생략이 가능하다.
        return "redirect: /basic/items";
    }

//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        // ModelAttribute는 생략이 가능하다.
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV4(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return  "basic/editForm";
    }

    @PostConstruct
    public void init() {
        Item item1 = new Item("ItemA", 10000, 10);
        Item item2 = new Item("ItemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);
    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, Item item) {
        itemRepository.update(itemId, item);

        return "redirect:/basic/items/{itemId}";
    }
}
