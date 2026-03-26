package com.example.todo.controller;

import com.example.todo.entity.Task;
import com.example.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * タスクコントローラー
 * HTTPリクエストを受け取り、サービスに処理を委譲して結果を返す。
 * @RestController をつけることで、戻り値が自動的にJSONに変換される。
 * @RequestMapping でエンドポイントの共通パスを定義する。
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * コンストラクタインジェクション。
     * Spring が TaskService を自動で渡してくれる。
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * GET /api/tasks
     * タスクを全件取得する。
     * @return タスクの一覧（JSON配列）
     */
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    /**
     * POST /api/tasks
     * 新しいタスクを作成する。
     * リクエストボディ例: { "title": "買い物をする" }
     * @ResponseStatus(CREATED) でHTTPステータス201を返す
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Map<String, String> body) {
        String title = body.get("title");
        return taskService.createTask(title);
    }

    /**
     * PUT /api/tasks/{id}/toggle
     * タスクの完了状態を切り替える。
     * @PathVariable でURLの{id}部分を受け取る
     */
    @PutMapping("/{id}/toggle")
    public Task toggleDone(@PathVariable Long id) {
        return taskService.toggleDone(id);
    }

    /**
     * DELETE /api/tasks/{id}
     * タスクを削除する。
     * @ResponseStatus(NO_CONTENT) でHTTPステータス204を返す
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
