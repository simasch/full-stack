import {
    a as H,
    a0 as O,
    a1 as q,
    a7 as N,
    a8 as I,
    B as C,
    b as M,
    E as J,
    F as j,
    H as L,
    j as m,
    J as B,
    M as F,
    n as A,
    x as p,
    Y as U
} from "./copilot-Y7NsXdwP.js";
import {r as P} from "./state-D9hUSHhi.js";
import {B as V} from "./base-panel-Cm-eTOpq.js";
import {i as y} from "./icons-wGs4ytLT.js";
import {e as k} from "./early-project-state-CqEloDes.js";

const W = "copilot-info-panel{--dev-tools-red-color: red;--dev-tools-grey-color: gray;--dev-tools-green-color: green;position:relative}copilot-info-panel div.info-tray{display:flex;flex-direction:column;gap:10px}copilot-info-panel vaadin-button{margin-inline:var(--lumo-space-l)}copilot-info-panel dl{display:grid;grid-template-columns:auto auto;gap:0;margin:var(--space-100) var(--space-50);font:var(--font-xsmall)}copilot-info-panel dl>dt,copilot-info-panel dl>dd{padding:3px 10px;margin:0;white-space:nowrap;overflow:hidden;text-overflow:ellipsis}copilot-info-panel dd.live-reload-status>span{overflow:hidden;text-overflow:ellipsis;display:block;color:var(--status-color)}copilot-info-panel dd span.hidden{display:none}copilot-info-panel dd span.true{color:var(--dev-tools-green-color);font-size:large}copilot-info-panel dd span.false{color:var(--dev-tools-red-color);font-size:large}copilot-info-panel code{white-space:nowrap;-webkit-user-select:all;user-select:all}copilot-info-panel .checks{display:inline-grid;grid-template-columns:auto 1fr;gap:var(--space-50)}copilot-info-panel span.hint{font-size:var(--font-size-0);background:var(--gray-50);padding:var(--space-75);border-radius:var(--radius-2)}";
var D, S;
function _() {
  return S || (S = 1, D = function() {
    var e = document.getSelection();
    if (!e.rangeCount)
      return function() {
      };
    for (var t = document.activeElement, o = [], s = 0; s < e.rangeCount; s++)
      o.push(e.getRangeAt(s));
    switch (t.tagName.toUpperCase()) {
      // .toUpperCase handles XHTML
      case "INPUT":
      case "TEXTAREA":
        t.blur();
        break;
      default:
        t = null;
        break;
    }
    return e.removeAllRanges(), function() {
      e.type === "Caret" && e.removeAllRanges(), e.rangeCount || o.forEach(function(l) {
        e.addRange(l);
      }), t && t.focus();
    };
  }), D;
}
var E, $;
function z() {
  if ($) return E;
  $ = 1;
  var e = _(), t = {
    "text/plain": "Text",
    "text/html": "Url",
    default: "Text"
  }, o = "Copy to clipboard: #{key}, Enter";
  function s(n) {
    var a = (/mac os x/i.test(navigator.userAgent) ? "⌘" : "Ctrl") + "+C";
    return n.replace(/#{\s*key\s*}/g, a);
  }
  function l(n, a) {
    var i, u, f, g, d, r, h = !1;
    a || (a = {}), i = a.debug || !1;
    try {
      f = e(), g = document.createRange(), d = document.getSelection(), r = document.createElement("span"), r.textContent = n, r.ariaHidden = "true", r.style.all = "unset", r.style.position = "fixed", r.style.top = 0, r.style.clip = "rect(0, 0, 0, 0)", r.style.whiteSpace = "pre", r.style.webkitUserSelect = "text", r.style.MozUserSelect = "text", r.style.msUserSelect = "text", r.style.userSelect = "text", r.addEventListener("copy", function(c) {
        if (c.stopPropagation(), a.format)
          if (c.preventDefault(), typeof c.clipboardData > "u") {
            i && console.warn("unable to use e.clipboardData"), i && console.warn("trying IE specific stuff"), window.clipboardData.clearData();
            var x = t[a.format] || t.default;
            window.clipboardData.setData(x, n);
          } else
            c.clipboardData.clearData(), c.clipboardData.setData(a.format, n);
        a.onCopy && (c.preventDefault(), a.onCopy(c.clipboardData));
      }), document.body.appendChild(r), g.selectNodeContents(r), d.addRange(g);
      var R = document.execCommand("copy");
      if (!R)
        throw new Error("copy command was unsuccessful");
      h = !0;
    } catch (c) {
      i && console.error("unable to copy using execCommand: ", c), i && console.warn("trying IE specific stuff");
      try {
        window.clipboardData.setData(a.format || "text", n), a.onCopy && a.onCopy(window.clipboardData), h = !0;
      } catch (x) {
        i && console.error("unable to copy using clipboardData: ", x), i && console.error("falling back to prompt"), u = s("message" in a ? a.message : o), window.prompt(u, n);
      }
    } finally {
      d && (typeof d.removeRange == "function" ? d.removeRange(g) : d.removeAllRanges()), r && document.body.removeChild(r), f();
    }
    return h;
  }
  return E = l, E;
}
var G = z();
const K = /* @__PURE__ */ H(G);
var X = Object.defineProperty, Y = Object.getOwnPropertyDescriptor, w = (e, t, o, s) => {
  for (var l = s > 1 ? void 0 : s ? Y(t, o) : t, n = e.length - 1, a; n >= 0; n--)
    (a = e[n]) && (l = (s ? a(t, o, l) : a(l)) || l);
  return s && l && X(t, o, l), l;
};
let b = class extends V {
  constructor() {
    super(...arguments), this.serverInfo = [], this.clientInfo = [{ name: "Browser", version: navigator.userAgent }], this.handleServerInfoEvent = (e) => {
      const t = JSON.parse(e.data.info);
      this.serverInfo = t.versions, N().then((o) => {
        o && (this.clientInfo.unshift({ name: "Vaadin Employee", version: "true", more: void 0 }), this.requestUpdate("clientInfo"));
      }), C() === "success" && U("hotswap-active", { value: j() });
    };
  }
  connectedCallback() {
    super.connectedCallback(), this.onCommand("copilot-info", this.handleServerInfoEvent), this.onEventBus("system-info-with-callback", (e) => {
      e.detail.callback(this.getInfoForClipboard(e.detail.notify));
    }), this.reaction(
      () => m.idePluginState,
      () => {
        this.requestUpdate("serverInfo");
      }
    );
  }
  getIndex(e) {
    return this.serverInfo.findIndex((t) => t.name === e);
  }
  render() {
    const e = [...this.serverInfo, ...this.clientInfo];
    let t = this.getIndex("Spring") + 1;
    return t === 0 && (t = e.length), k.springSecurityEnabled && (e.splice(t, 0, { name: "Spring Security", version: "true" }), t++), k.springJpaDataEnabled && (e.splice(t, 0, { name: "Spring Data JPA", version: "true" }), t++), p` <style>
        ${W}
      </style>
      <div class="info-tray">
        <dl>
          ${e.map(
      (o) => p`
              <dt>${o.name}</dt>
              <dd title="${o.version}" style="${o.name === "Java Hotswap" ? "white-space: normal" : ""}">
                ${this.renderValue(o.version)} ${o.more}
              </dd>
            `
    )}
          ${this.renderDevWorkflowSection()}
        </dl>
        ${this.renderDevelopmentWorkflowButton()}
      </div>`;
  }
  renderDevWorkflowSection() {
    const e = C(), t = this.getIdePluginLabelText(m.idePluginState), o = this.getHotswapAgentLabelText(e);
    return p`
      <dt>Java Hotswap</dt>
      <dd>${v(e === "success")} ${o}</dd>
      ${I() !== "unsupported" ? p`<dt>IDE Plugin</dt>
            <dd>${v(I() === "success")} ${t}</dd>` : J}
    `;
  }
  renderDevelopmentWorkflowButton() {
    const e = B();
    let t = "", o = null;
    return e.status === "success" ? (t = "More details...", o = y.successColorful) : e.status === "warning" ? (t = "Improve Development Workflow...", o = y.warningColorful) : e.status === "error" && (t = "Fix Development Workflow...", o = p`<span style="color: var(--red)">${y.error}</span>`), p`
      <vaadin-button
        id="development-workflow-guide"
        @click="${() => {
      L();
    }}">
        <span slot="prefix"> ${o}</span>
        ${t}</vaadin-button
      >
    `;
  }
  getHotswapAgentLabelText(e) {
    return e === "success" ? "Java Hotswap is enabled" : e === "error" ? "Hotswap is partially enabled" : "Hotswap is not enabled";
  }
  getIdePluginLabelText(e) {
    if (I() !== "success")
      return "Not installed";
    if (e?.version) {
      let t = null;
      return e?.ide && (e?.ide === "intellij" ? t = "IntelliJ" : e?.ide === "vscode" ? t = "VS Code" : e?.ide === "eclipse" && (t = "Eclipse")), t ? `${e?.version} ${t}` : e?.version;
    }
    return "Not installed";
  }
  renderValue(e) {
    return e === "false" ? v(!1) : e === "true" ? v(!0) : e;
  }
  getInfoForClipboard(e) {
    const t = this.renderRoot.querySelectorAll(".info-tray dt"), l = Array.from(t).map((n) => ({
      key: n.textContent.trim(),
      value: n.nextElementSibling.textContent.trim()
    })).filter((n) => n.key !== "Live reload").filter((n) => !n.key.startsWith("Vaadin Emplo")).map((n) => {
      const { key: a } = n;
      let { value: i } = n;
      if (a === "IDE Plugin")
        i = this.getIdePluginLabelText(m.idePluginState) ?? "false";
      else if (a === "Java Hotswap") {
        const u = m.jdkInfo?.jrebel, f = C();
        u && f === "success" ? i = "JRebel is in use" : i = this.getHotswapAgentLabelText(f);
      }
      return `${a}: ${i}`;
    }).join(`
`);
    return e && O({
      type: q.INFORMATION,
      message: "Environment information copied to clipboard",
      dismissId: "versionInfoCopied"
    }), l.trim();
  }
};
w([
  P()
], b.prototype, "serverInfo", 2);
w([
  P()
], b.prototype, "clientInfo", 2);
b = w([
  A("copilot-info-panel")
], b);
let T = class extends F {
  createRenderRoot() {
    return this;
  }
  connectedCallback() {
    super.connectedCallback(), this.style.display = "flex";
  }
  render() {
    return p`<button title="Copy to clipboard" aria-label="Copy to clipboard" theme="icon tertiary">
      <span
        @click=${() => {
      M.emit("system-info-with-callback", {
        callback: K,
        notify: !0
      });
    }}
        >${y.copy}</span
      >
    </button>`;
  }
};
T = w([
  A("copilot-info-actions")
], T);
const Q = {
  header: "Info",
  expanded: !1,
  panelOrder: 15,
  panel: "right",
  floating: !1,
  tag: "copilot-info-panel",
  actionsTag: "copilot-info-actions",
  eager: !0
  // Render even when collapsed as error handling depends on this
}, Z = {
  init(e) {
    e.addPanel(Q);
  }
};
window.Vaadin.copilot.plugins.push(Z);
function v(e) {
  return e ? p`<span class="true">☑</span>` : p`<span class="false">☒</span>`;
}
export {
  T as Actions,
  b as CopilotInfoPanel
};
