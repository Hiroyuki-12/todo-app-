package com.example.todo.repository;

import com.example.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * タスクリポジトリ
 * JpaRepository を継承することで、DB操作のメソッドが自動で使えるようになる。
 * 例: findAll()（全件取得）, save()（保存）, deleteById()（削除）など。
 * 独自のクエリが必要な場合はここにメソッドを追加する。
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
