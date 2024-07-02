package com.team12.JavaTourPromotion.controller.INSPECTOR;

import com.team12.JavaTourPromotion.GetVM.CommentGetVM;
import com.team12.JavaTourPromotion.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/inspector")
public class InspectorControllerAPI {

    private final CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<List<CommentGetVM>> getAllComments(){
        var list = commentService.getAllComments();
        if(list.isEmpty())
            throw new RuntimeException("List of comments is empty!");
        else
            return ResponseEntity.ok(list);
    }

    @PutMapping("/comment/hide/{id}")
    public ResponseEntity hideComment(@PathVariable Long id){
        commentService.hideComment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/comment/unhide/{id}")
    public ResponseEntity unhideComment(@PathVariable Long id){
        commentService.unhideComment(id);
        return ResponseEntity.ok().build();
    }
}
