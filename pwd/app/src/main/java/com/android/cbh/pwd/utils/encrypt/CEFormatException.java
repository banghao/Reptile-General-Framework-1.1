/*
 * Copyright (c) 1995, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.android.cbh.pwd.utils.encrypt;

import java.io.IOException;
//方法调用中参数的格式不符合对应的形参类型的格式时,输入字符串的格式不正确

public class CEFormatException extends IOException {
        public CEFormatException(String s) {
                super(s);
        }
}
//为什么抛异常：抛异常的性能是非常差的。通常来说,抛一个
//        异常大概会消耗100到1000个时钟节拍。通常是出现了意想不到的错误,我们才会往外抛异常
//        BASE64Decoder之所以性能很差就是因为它通过抛异常来对外请求道,”我还需要更多的数据“:
