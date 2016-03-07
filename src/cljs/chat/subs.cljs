(ns chat.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame
                             :refer [register-sub]]))

(register-sub
 :name
 (fn [db]
   (reaction (:name @db))))

(register-sub
 :messages
 (fn [db]
  (reaction (:messages @db))))

(register-sub
 :channels
 (fn [db]
  (reaction (keys (:channels @db)))))

(register-sub :broadcast
 (fn [db] (reaction (get-in @db [:broadcast :text]))))
