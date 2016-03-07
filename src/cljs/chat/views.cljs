(ns chat.views
  (:require [re-frame.core :as re-frame
                           :refer [subscribe
                                   dispatch]]
            [chat.skyblue :refer [whatevs]]))

(defn online-list []
 (let [channels (re-frame/subscribe [:channels])]
  (fn []
   [:div {:class "online"}
      (for [channel @channels]
       ^{:key channel} [:div channel])])))

(defn messages-list []
 (let [messages (re-frame/subscribe [:messages])]
  (fn []
    [:div
     (for [msg (rseq @messages)]
      [:div [:div [:code (:id msg)]]
            [:pre (:data msg)]])])))

(defn broadcast-form []
 (fn []
  (let [text (subscribe [:broadcast])]
    [:form {:class "row"
            :on-submit (fn [event]
                         (.preventDefault event)
                         (dispatch [:broadcast @text]))}
      [:input {:type "string"
               :class "col xs-8"
               :value @text
               :on-change #(dispatch [:broadcast-text-changed
                                      (-> % .-target .-value)])}]
      [:input {:type "submit"
               :value "Send"
               :class "col md-4 btn btn-sm"}]])))
  

(defn main-panel []
 (fn []
   [:div {:class "container"}
    [:div {:class "row"}
     [:div {:class "col md-12"}
      [:h1 "WebRTC chat"]
      [:hr]]]
    [:div {:class "row"}
     [:div {:class "col md-4"}
      [online-list]]
     [:div {:class "col md-8"}
      [broadcast-form]
      [messages-list]]]]))
  
