<template>
  <a-drawer
    title="修改杂志社"
    :maskClosable="false"
    width=650
    placement="right"
    :closable="false"
    @close="onClose"
    :visible="editVisiable"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">
    <a-form :form="form">
      <a-form-item label='名称' v-bind="formItemLayout">
        <a-input style="width: 100%"
                        v-decorator="['name',
                   {rules: [
                    { required: true, message: '不能为空'},
                    { max: 20, message: '长度不能超过20个字符'}
                  ]}]"/>
      </a-form-item>
    </a-form>
    <div class="drawer-bootom-button">
      <a-popconfirm title="确定放弃编辑？" @confirm="onClose" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>
      <a-button @click="handleSubmit" type="primary" :loading="loading">提交</a-button>
    </div>
  </a-drawer>
</template>
<script>
const formItemLayout = {
  labelCol: { span: 3 },
  wrapperCol: { span: 18 }
}
export default {
  name: 'NewspaperOfficeEdit',
  props: {
    editVisiable: {
      default: false
    }
  },
  data () {
    return {
      loading: false,
      formItemLayout,
      form: this.$form.createForm(this),
      model: {}
    }
  },
  methods: {
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    setFormValues ({...model}) {
      let fields = ['name']
      Object.keys(model).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          let obj = {}
          obj[key] = model[key]
          this.form.setFieldsValue(obj)
        }
      })
      this.model.id = model.id
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        if (!err) {
          let model = this.form.getFieldsValue()
          model.id = this.model.id
          this.$put('newspaperOffice', {
            ...model
          }).then(() => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>
