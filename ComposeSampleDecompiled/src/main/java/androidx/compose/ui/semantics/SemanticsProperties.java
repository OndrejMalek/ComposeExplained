package androidx.compose.ui.semantics;

import androidx.compose.ui.semantics.SemanticsPropertyKey;

/* loaded from: classes.dex */
public abstract class SemanticsProperties {
    public static final SemanticsPropertyKey CollectionInfo;
    public static final SemanticsPropertyKey CollectionItemInfo;
    public static final SemanticsPropertyKey ContentDescription = new SemanticsPropertyKey("ContentDescription", SemanticsPropertyKey.AnonymousClass1.INSTANCE$1);
    public static final SemanticsPropertyKey Disabled;
    public static final SemanticsPropertyKey EditableText;
    public static final SemanticsPropertyKey Error;
    public static final SemanticsPropertyKey Focused;
    public static final SemanticsPropertyKey Heading;
    public static final SemanticsPropertyKey HorizontalScrollAxisRange;
    public static final SemanticsPropertyKey InvisibleToUser;
    public static final SemanticsPropertyKey IsTraversalGroup;
    public static final SemanticsPropertyKey LiveRegion;
    public static final SemanticsPropertyKey PaneTitle;
    public static final SemanticsPropertyKey Password;
    public static final SemanticsPropertyKey ProgressBarRangeInfo;
    public static final SemanticsPropertyKey Role;
    public static final SemanticsPropertyKey SelectableGroup;
    public static final SemanticsPropertyKey Selected;
    public static final SemanticsPropertyKey StateDescription;
    public static final SemanticsPropertyKey TestTag;
    public static final SemanticsPropertyKey Text;
    public static final SemanticsPropertyKey TextSelectionRange;
    public static final SemanticsPropertyKey ToggleableState;
    public static final SemanticsPropertyKey TraversalIndex;
    public static final SemanticsPropertyKey VerticalScrollAxisRange;

    static {
        SemanticsPropertyKey.AnonymousClass1 anonymousClass1 = SemanticsPropertyKey.AnonymousClass1.INSTANCE;
        StateDescription = new SemanticsPropertyKey("StateDescription", anonymousClass1);
        ProgressBarRangeInfo = new SemanticsPropertyKey("ProgressBarRangeInfo", anonymousClass1);
        PaneTitle = new SemanticsPropertyKey("PaneTitle", SemanticsPropertyKey.AnonymousClass1.INSTANCE$5);
        SelectableGroup = new SemanticsPropertyKey("SelectableGroup", anonymousClass1);
        CollectionInfo = new SemanticsPropertyKey("CollectionInfo", anonymousClass1);
        CollectionItemInfo = new SemanticsPropertyKey("CollectionItemInfo", anonymousClass1);
        Heading = new SemanticsPropertyKey("Heading", anonymousClass1);
        Disabled = new SemanticsPropertyKey("Disabled", anonymousClass1);
        LiveRegion = new SemanticsPropertyKey("LiveRegion", anonymousClass1);
        Focused = new SemanticsPropertyKey("Focused", anonymousClass1);
        IsTraversalGroup = new SemanticsPropertyKey("IsTraversalGroup", anonymousClass1);
        InvisibleToUser = new SemanticsPropertyKey("InvisibleToUser", SemanticsPropertyKey.AnonymousClass1.INSTANCE$2);
        TraversalIndex = new SemanticsPropertyKey("TraversalIndex", SemanticsPropertyKey.AnonymousClass1.INSTANCE$9);
        HorizontalScrollAxisRange = new SemanticsPropertyKey("HorizontalScrollAxisRange", anonymousClass1);
        VerticalScrollAxisRange = new SemanticsPropertyKey("VerticalScrollAxisRange", anonymousClass1);
        Role = new SemanticsPropertyKey("Role", SemanticsPropertyKey.AnonymousClass1.INSTANCE$6);
        TestTag = new SemanticsPropertyKey("TestTag", SemanticsPropertyKey.AnonymousClass1.INSTANCE$7);
        Text = new SemanticsPropertyKey("Text", SemanticsPropertyKey.AnonymousClass1.INSTANCE$8);
        EditableText = new SemanticsPropertyKey("EditableText", anonymousClass1);
        TextSelectionRange = new SemanticsPropertyKey("TextSelectionRange", anonymousClass1);
        Selected = new SemanticsPropertyKey("Selected", anonymousClass1);
        ToggleableState = new SemanticsPropertyKey("ToggleableState", anonymousClass1);
        Password = new SemanticsPropertyKey("Password", anonymousClass1);
        Error = new SemanticsPropertyKey("Error", anonymousClass1);
    }
}
