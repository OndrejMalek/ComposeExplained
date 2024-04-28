package androidx.compose.ui.semantics;

import androidx.compose.ui.semantics.SemanticsPropertyKey;

/* loaded from: classes.dex */
public abstract class SemanticsActions {
    public static final SemanticsPropertyKey Collapse;
    public static final SemanticsPropertyKey CopyText;
    public static final SemanticsPropertyKey CustomActions;
    public static final SemanticsPropertyKey CutText;
    public static final SemanticsPropertyKey Dismiss;
    public static final SemanticsPropertyKey Expand;
    public static final SemanticsPropertyKey GetTextLayoutResult;
    public static final SemanticsPropertyKey OnClick;
    public static final SemanticsPropertyKey OnLongClick;
    public static final SemanticsPropertyKey PageDown;
    public static final SemanticsPropertyKey PageLeft;
    public static final SemanticsPropertyKey PageRight;
    public static final SemanticsPropertyKey PageUp;
    public static final SemanticsPropertyKey PasteText;
    public static final SemanticsPropertyKey PerformImeAction;
    public static final SemanticsPropertyKey RequestFocus;
    public static final SemanticsPropertyKey ScrollBy;
    public static final SemanticsPropertyKey SetProgress;
    public static final SemanticsPropertyKey SetSelection;
    public static final SemanticsPropertyKey SetText;

    static {
        SemanticsPropertyKey.AnonymousClass1 anonymousClass1 = SemanticsPropertyKey.AnonymousClass1.INSTANCE$11;
        GetTextLayoutResult = new SemanticsPropertyKey("GetTextLayoutResult", anonymousClass1);
        OnClick = new SemanticsPropertyKey("OnClick", anonymousClass1);
        OnLongClick = new SemanticsPropertyKey("OnLongClick", anonymousClass1);
        ScrollBy = new SemanticsPropertyKey("ScrollBy", anonymousClass1);
        SetProgress = new SemanticsPropertyKey("SetProgress", anonymousClass1);
        SetSelection = new SemanticsPropertyKey("SetSelection", anonymousClass1);
        SetText = new SemanticsPropertyKey("SetText", anonymousClass1);
        PerformImeAction = new SemanticsPropertyKey("PerformImeAction", anonymousClass1);
        CopyText = new SemanticsPropertyKey("CopyText", anonymousClass1);
        CutText = new SemanticsPropertyKey("CutText", anonymousClass1);
        PasteText = new SemanticsPropertyKey("PasteText", anonymousClass1);
        Expand = new SemanticsPropertyKey("Expand", anonymousClass1);
        Collapse = new SemanticsPropertyKey("Collapse", anonymousClass1);
        Dismiss = new SemanticsPropertyKey("Dismiss", anonymousClass1);
        RequestFocus = new SemanticsPropertyKey("RequestFocus", anonymousClass1);
        CustomActions = new SemanticsPropertyKey("CustomActions", SemanticsPropertyKey.AnonymousClass1.INSTANCE);
        PageUp = new SemanticsPropertyKey("PageUp", anonymousClass1);
        PageLeft = new SemanticsPropertyKey("PageLeft", anonymousClass1);
        PageDown = new SemanticsPropertyKey("PageDown", anonymousClass1);
        PageRight = new SemanticsPropertyKey("PageRight", anonymousClass1);
    }
}
