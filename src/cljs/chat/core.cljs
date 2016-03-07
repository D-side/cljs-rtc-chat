(ns chat.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [chat.handlers]
              [chat.subs]
              [chat.views :as views]
              [chat.config :as config]))

(when config/debug?
  (println "Running in dev mode"))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init [] 
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
