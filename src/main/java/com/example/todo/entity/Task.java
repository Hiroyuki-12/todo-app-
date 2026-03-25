package com.example.todo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * タスクエンティティ
 * DBの tasks テーブルと対応するクラス。
 * JPAが起動時にテーブルを自動生成・更新する（ddl-auto: update）。
 */
@Entity
@Table(name = "tasks")
public class Task {

    /** 主キー。INSERT時にDBが自動採番（AUTO_INCREMENT）する */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** タスクのタイトル。NULL不可 */
    @Column(nullable = false)
    private String title;

    /** 完了フラグ。デフォルトは未完了（false） */
    @Column(nullable = false)
    private boolean done = false;

    /** 作成日時。NULL不可、更新不可（一度セットしたら変わらない） */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * DB保存直前に自動実行されるメソッド。
     * 作成日時をアプリ側でセットする。
     */
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // --- getter / setter ---
    // JPAやJSONシリアライズ時にフィールドへアクセスするために必要

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
