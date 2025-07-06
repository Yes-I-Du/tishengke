import React from "react";
import "./index.css";

/**
 * 全局底部栏组件
 * @constructor
 */
export default function GlobalFooter() {
  const currentYear = new Date().getFullYear();

  return (
    <div className="global-footer">
      <div>© {currentYear} 题胜客智能题库</div>
      <div>
        <a href="https://www.xxxxxxxxxxx.cn" target="_blank">
          作者：小小程序员 - 程序员11257
        </a>
      </div>
    </div>
  );
}