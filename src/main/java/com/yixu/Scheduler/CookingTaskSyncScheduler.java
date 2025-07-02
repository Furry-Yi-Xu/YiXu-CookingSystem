package com.yixu.Scheduler;

import com.yixu.Task.AbstractTask.CookingTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class CookingTaskSyncScheduler {

    private final Map<Location, List<CookingTask>> activeTasks = new HashMap<>();

    public void addTask(Location location, CookingTask task) {
        activeTasks.computeIfAbsent(location, k -> new ArrayList<>()).add(task);
    }

    public void removeAllTasks(Location location) {
        activeTasks.remove(location);
    }

    public void start(Plugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                tickTasks();
            }
        }.runTaskTimer(plugin, 0, 20);
    }

    private void tickTasks() {
        Iterator<Map.Entry<Location, List<CookingTask>>> iterator = activeTasks.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Location, List<CookingTask>> entry = iterator.next();
            List<CookingTask> taskList = entry.getValue();

            taskList.removeIf(task -> {
                task.cookingTask();
                return task.isFinished();
            });

            if (taskList.isEmpty()) {
                iterator.remove();
            }
        }
    }
}
