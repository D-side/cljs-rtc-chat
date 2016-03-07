(ns chat.handlers
    (:require [re-frame.core :refer [dispatch register-handler path]
                             :as re-frame]
              [chat.db :as db]
              [chat.rtc :refer [rtc]]))

(register-handler :initialize-db
 (fn  [_ _]
   (doto (rtc {:ice [{:urls ["stun:stun.l.google.com:19302"
                             "stun:stun1.l.google.com:19302"
                             "stun:stun2.l.google.com:19302"
                             "stun:stun3.l.google.com:19302"
                             "stun:stun4.l.google.com:19302"]}]
               :constraints nil
               :channels {:test true}})
     (.on "channel:opened:test"
       (fn [id channel]
         (set! (.-onmessage channel)
           #(dispatch
              [:message id (.-data %)]))
         (dispatch [:channel-opened id channel])))
     (.on "channel:closed:test"
       (fn [id channel]
         (dispatch [:channel-closed id]))))
   db/default-db))

(register-handler :channel-opened
 (path [:channels])
 (fn [channels [_ id channel]]
   (assoc channels id channel)))

(register-handler :channel-closed
 (path [:channels])
 (fn [channels [_ id]]
   (dissoc channels id)))

(register-handler :message
 (path [:messages])
 (fn [db [_ id data]]
  (println id " " data)
  (conj db {:id id
            :data data})))

(register-handler :broadcast-text-changed
 (path [:broadcast :text])
 (fn [_ [_ v]] v))

(register-handler :broadcast
 (fn [db [_ text]]
   (println text)
   (doseq [[_ channel] (:channels db)]
     (.send channel text))
   (merge db {:messages
              (conj (:messages db)
                 {:id "me"
                  :data text})
              :broadcast {:text ""}})))
   

