# DB設計書

## 基本情報

| 項目 | 内容 |
|------|------|
| データベース名 | todo_app |
| DBMS | MySQL 8.0.41 |

## テーブル一覧

| # | テーブル名 | 説明 |
|---|------------|------|
| 1 | tasks | タスク情報 |

---

## tasksテーブル

| カラム名 | データ型 | NULL | デフォルト | 説明 |
|----------|----------|------|------------|------|
| id | BIGINT | NOT NULL | AUTO_INCREMENT | 主キー |
| title | VARCHAR(255) | NOT NULL | - | タスク名 |
| done | BOOLEAN | NOT NULL | false | 完了フラグ |
| created_at | DATETIME | NOT NULL | CURRENT_TIMESTAMP | 作成日時 |

### DDL

```sql
CREATE DATABASE IF NOT EXISTS todo_app;

USE todo_app;

CREATE TABLE tasks (
  id         BIGINT       NOT NULL AUTO_INCREMENT,
  title      VARCHAR(255) NOT NULL,
  done       BOOLEAN      NOT NULL DEFAULT false,
  created_at DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
```

## クラス設計

| クラス名 | 役割 |
|----------|------|
| Task | Entityクラス。tasksテーブルに対応 |
| TaskRepository | DB操作インターフェース（JPA） |
| TaskService | ビジネスロジック |
| TaskController | REST APIのエンドポイント定義 |
