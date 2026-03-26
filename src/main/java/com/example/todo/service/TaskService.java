package com.example.todo.service;

import com.example.todo.entity.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * タスクサービス
 * ビジネスロジックを担当するクラス。
 * Controller と Repository の間に立ち、処理の流れを制御する。
 * @Service をつけることで Spring が自動的に管理してくれる（DIコンテナに登録される）。
 */
@Service
public class TaskService {

    /** リポジトリをDI（依存性注入）で受け取る */
    private final TaskRepository taskRepository;

    /**
     * コンストラクタインジェクション。
     * Spring が TaskRepository を自動で渡してくれる。
     */
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * タスクを全件取得する。
     * @return タスクの一覧
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * 新しいタスクを作成して保存する。
     * @param title タスクのタイトル
     * @return 保存されたタスク（idとcreated_atが自動セットされた状態）
     */
    public Task createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        return taskRepository.save(task);
    }

    /**
     * タスクの完了状態を切り替える（trueならfalse、falseならtrue）。
     * @param id 対象タスクのID
     * @return 更新後のタスク
     * @throws IllegalArgumentException 指定IDのタスクが存在しない場合
     */
    public Task toggleDone(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("タスクが見つかりません: id=" + id));
        task.setDone(!task.isDone());
        return taskRepository.save(task);
    }

    /**
     * タスクを削除する。
     * @param id 削除対象タスクのID
     * @throws IllegalArgumentException 指定IDのタスクが存在しない場合
     */
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("タスクが見つかりません: id=" + id);
        }
        taskRepository.deleteById(id);
    }
}
