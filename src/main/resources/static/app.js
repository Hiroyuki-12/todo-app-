const API_URL = '/api/tasks';

// ページ読み込み時にタスクを取得して表示する
document.addEventListener('DOMContentLoaded', fetchTasks);

// 追加ボタンのクリックイベント
document.getElementById('add-btn').addEventListener('click', addTask);

// Enterキーでも追加できるようにする
document.getElementById('new-task-title').addEventListener('keydown', (e) => {
  if (e.key === 'Enter') addTask();
});

/**
 * サーバーからタスク一覧を取得して画面に表示する
 */
async function fetchTasks() {
  const res = await fetch(API_URL);
  const tasks = await res.json();
  renderTasks(tasks);
}

/**
 * タスク一覧をHTMLとして描画する
 */
function renderTasks(tasks) {
  const list = document.getElementById('task-list');
  list.innerHTML = '';

  tasks.forEach(task => {
    const li = document.createElement('li');
    li.className = 'task-item';

    // タイトル
    const title = document.createElement('span');
    title.className = 'task-title' + (task.done ? ' done' : '');
    title.textContent = task.title;

    // 完了切り替えボタン
    const toggleBtn = document.createElement('button');
    toggleBtn.className = 'toggle-btn';
    toggleBtn.textContent = task.done ? '戻す' : '完了';
    toggleBtn.addEventListener('click', () => toggleTask(task.id));

    // 削除ボタン
    const deleteBtn = document.createElement('button');
    deleteBtn.className = 'delete-btn';
    deleteBtn.textContent = '削除';
    deleteBtn.addEventListener('click', () => deleteTask(task.id));

    li.appendChild(title);
    li.appendChild(toggleBtn);
    li.appendChild(deleteBtn);
    list.appendChild(li);
  });
}

/**
 * 新しいタスクをサーバーに送信して追加する
 */
async function addTask() {
  const input = document.getElementById('new-task-title');
  const title = input.value.trim();
  if (!title) return; // 空欄なら何もしない

  await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title })
  });

  input.value = '';
  fetchTasks(); // 一覧を再取得して再描画
}

/**
 * タスクの完了状態を切り替える
 */
async function toggleTask(id) {
  await fetch(`${API_URL}/${id}/toggle`, { method: 'PUT' });
  fetchTasks();
}

/**
 * タスクを削除する
 */
async function deleteTask(id) {
  await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
  fetchTasks();
}
