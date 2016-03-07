(ns chat.rtc)

(defn rtc
  "A minimal CLJS bridge to rtc-quickconnect

Provides only argument conversion at the moment"
  [options]
  (js/RTC
    (clj->js options)))
