## A simple (almost) serverless WebRTC chat

Uses [`re-frame`](https://github.com/Day8/re-frame) (meaning, ClojureScript, React.js and Reagent as well) under the hood.

Uses Bower dependencies via `lein-bower` and `wiredep` (I have it globally).

This is a toy and will likely always be a work-in-progress. I'll be really glad if anyone finds the code here even remotely useful, but it hasn't been cleaned up in any way before being published.

* Once I found SkyBlue I pretty much stopped using `garden`
* Events are a bit of a mess, I haven't found a way to scope them down yet
* Wraps [`rtc`](http://rtc.io) for interaction over WebRTC with a dead-simple wrapper and a couple of event handlers; almost the entire code for that is shoved into initializer and should be extracted into a namespace

## Development Mode

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

## Production Build

```
lein clean
lein cljsbuild once min
```

## Contributions

I'm not expecting any. You'll really surprise me in a good way if you pay this project this much attention. *GitHub issue* all the things, I'll take it from there.
